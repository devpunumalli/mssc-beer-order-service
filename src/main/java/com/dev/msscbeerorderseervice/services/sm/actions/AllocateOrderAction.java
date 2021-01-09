package com.dev.msscbeerorderseervice.services.sm.actions;

import com.dev.brewery.model.events.AllocateOrderRequest;
import com.dev.msscbeerorderseervice.config.JmsConfig;
import com.dev.msscbeerorderseervice.domain.BeerOrder;
import com.dev.msscbeerorderseervice.domain.BeerOrderEventEnum;
import com.dev.msscbeerorderseervice.domain.BeerOrderStatusEnum;
import com.dev.msscbeerorderseervice.repositories.BeerOrderRepository;
import com.dev.msscbeerorderseervice.services.BeerOrderManagerImpl;
import com.dev.msscbeerorderseervice.web.mappers.BeerOrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderAction implements Action<BeerOrderStatusEnum,BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final JmsTemplate jmsTemplate;
    private final BeerOrderMapper beerOrderMapper;
    /**
     * Execute action with a {@link StateContext}.
     *
     * @param context the state context
     */
    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String)context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));
jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER,
                           AllocateOrderRequest.builder().beerOrderDto(beerOrderMapper.beerOrderToDto(beerOrder)).build());
log.debug("Allocation request for this  order id " + beerOrderId + "is sent");

    }
}
