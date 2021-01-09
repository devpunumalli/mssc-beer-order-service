package com.dev.msscbeerorderseervice.services;

import com.dev.msscbeerorderseervice.domain.BeerOrder;
import com.dev.msscbeerorderseervice.domain.BeerOrderEventEnum;
import com.dev.msscbeerorderseervice.domain.BeerOrderStatusEnum;
import com.dev.msscbeerorderseervice.repositories.BeerOrderRepository;
import com.dev.msscbeerorderseervice.services.sm.BeerOrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Slf4j
@RequiredArgsConstructor
public class BeerOrderManagerImpl implements BeerOrderManager {
    public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER" ;
    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderStateChangeInterceptor interceptor;
    private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> sm;

    @Transactional
    @Override
    public BeerOrder newOrder(BeerOrder beerOrder) {
        beerOrder.setId(null);
        beerOrder.setOrderStatus(BeerOrderStatusEnum.NEW);

        BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);

        //need to send an event from the stat machine.Spring Message class can be used to send that event
        sendNewBeerOrderEvent(savedBeerOrder,BeerOrderEventEnum.VALIDATE_ORDER);
        return savedBeerOrder;
    }

    @Override
    public void processValidationResult(UUID beerOrderId, Boolean isValid) {
        BeerOrder beerOrder = beerOrderRepository.getOne(beerOrderId);
        if(isValid){
            sendNewBeerOrderEvent(beerOrder,BeerOrderEventEnum.VALIDATION_PASSED);
            BeerOrder validatedOrder = beerOrderRepository.getOne(beerOrderId);
            sendNewBeerOrderEvent(validatedOrder,BeerOrderEventEnum.ALLOCATE_ORDER);
        }else{
            sendNewBeerOrderEvent(beerOrder,BeerOrderEventEnum.VALIDATION_FAILED);
        }
    }

    private void sendNewBeerOrderEvent(BeerOrder savedBeerOrder, BeerOrderEventEnum validateOrder) {
        StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = build(savedBeerOrder);
        Message<BeerOrderEventEnum> msg = MessageBuilder.withPayload(validateOrder)
                .setHeader(ORDER_ID_HEADER, savedBeerOrder.getId().toString())
                .build();
        sm.sendEvent(msg);
    }

    private StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> build(BeerOrder beerOrder){
        StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachine = sm.getStateMachine(beerOrder.getId());
        stateMachine.stop();
        stateMachine.getStateMachineAccessor().doWithAllRegions(sma->{
            sma.addStateMachineInterceptor(interceptor);
            sma.resetStateMachine(new DefaultStateMachineContext<>(beerOrder.getOrderStatus(),null,null,null));
        });
        stateMachine.start();
      return stateMachine;
    }
}
