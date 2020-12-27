package com.dev.msscbeerorderseervice.services;

import com.dev.msscbeerorderseervice.web.model.BeerOrderDto;
import com.dev.msscbeerorderseervice.web.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {

    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);
    BeerOrderDto getOrderById(UUID CustomerId,UUID OrderID);
    void  pickUpOrder(UUID customerId,UUID orderId);

}
