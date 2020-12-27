package com.dev.msscbeerorderseervice.services;


import com.dev.msscbeerorderseervice.domain.BeerOrder;
import com.dev.msscbeerorderseervice.domain.Customer;
import com.dev.msscbeerorderseervice.repositories.BeerOrderRepository;
import com.dev.msscbeerorderseervice.repositories.CustomerRepository;
import com.dev.msscbeerorderseervice.web.mappers.BeerOrderMapper;
import com.dev.msscbeerorderseervice.web.model.BeerOrderDto;
import com.dev.msscbeerorderseervice.web.model.BeerOrderPagedList;
import com.dev.msscbeerorderseervice.web.model.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BeerOrderServiceImpl implements BeerOrderService {


    private final BeerOrderRepository beerOrderRepository;
    private final CustomerRepository customerRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final ApplicationEventPublisher publisher;

    public BeerOrderServiceImpl(BeerOrderRepository beerOrderRepository, CustomerRepository customerRepository, BeerOrderMapper beerOrderMapper, ApplicationEventPublisher publisher) {
        this.beerOrderRepository = beerOrderRepository;
        this.customerRepository = customerRepository;
        this.beerOrderMapper = beerOrderMapper;
        this.publisher = publisher;
    }

    @Override
    public BeerOrderPagedList listOrders(UUID customerId, Pageable pageable) {


        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            Page<BeerOrder> beerOrdersPage = beerOrderRepository.findAllByCustomer(customer.get(), pageable);
            return  new BeerOrderPagedList(beerOrdersPage.stream()
                                                   .map(beerOrderMapper::beerOrderToDto)
                                                   .collect(Collectors.toList()),
                                           PageRequest.of(beerOrdersPage.getPageable().getPageNumber(),
                                                          beerOrdersPage.getPageable().getPageSize()),
                                           beerOrdersPage.getTotalElements());
        } else {
            return null;
        }
    }
    @Override
    public BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if (customerOptional.isPresent()) {
            BeerOrder beerOrder = beerOrderMapper.dtoToBeerOrder(beerOrderDto);
            beerOrder.setId(null); //should not be set by outside client
            beerOrder.setCustomer(customerOptional.get());
            beerOrder.setOrderStatus(OrderStatusEnum.NEW);

            beerOrder.getBeerOrderLines().forEach(line -> line.setBeerOrder(beerOrder));

            BeerOrder savedBeerOrder = beerOrderRepository.saveAndFlush(beerOrder);

            log.debug("Saved Beer Order: " + beerOrder.getId());

            //todo impl
            //  publisher.publishEvent(new NewBeerOrderEvent(savedBeerOrder));

            return beerOrderMapper.beerOrderToDto(savedBeerOrder);
        }else{
            throw new RuntimeException("customer Not Found");
        }
    }

    @Override
    public BeerOrderDto getOrderById(UUID customerId, UUID orderId) {

        return beerOrderMapper.beerOrderToDto(  getOrder(customerId,orderId));
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {
        BeerOrder beerOrder = getOrder(customerId, orderId);
        beerOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        beerOrderRepository.save(beerOrder);

    }
    private BeerOrder getOrder(UUID customerId, UUID orderId){
        Optional<Customer> customerOptional = customerRepository.findById(customerId);

        if(customerOptional.isPresent()){
            Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(orderId);

            if(beerOrderOptional.isPresent()){
                BeerOrder beerOrder = beerOrderOptional.get();

                // fall to exception if customer id's do not match - order not for customer
                if(beerOrder.getCustomer().getId().equals(customerId)){
                    return beerOrder;
                }
            }
            throw new RuntimeException("Beer Order Not Found");
        }
        throw new RuntimeException("Customer Not Found");
    }
}
