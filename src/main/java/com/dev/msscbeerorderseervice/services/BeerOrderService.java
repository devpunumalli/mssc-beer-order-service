package com.dev.msscbeerorderseervice.services;

import com.dev.brewery.model.BeerOrderDto;
import com.dev.brewery.model.BeerOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BeerOrderService {

    BeerOrderPagedList listOrders(UUID customerId, Pageable pageable);

    BeerOrderDto placeOrder(UUID customerId, BeerOrderDto beerOrderDto);

    BeerOrderDto getOrderById(UUID CustomerId, UUID OrderID);

    void pickupOrder(UUID customerId, UUID orderId);

}
