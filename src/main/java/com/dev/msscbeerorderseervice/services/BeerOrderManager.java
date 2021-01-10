package com.dev.msscbeerorderseervice.services;

import com.dev.brewery.model.BeerOrderDto;
import com.dev.msscbeerorderseervice.domain.BeerOrder;

import java.util.UUID;

public interface BeerOrderManager {

    BeerOrder newOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);

    void beerOrderAllocationPassed(BeerOrderDto beerOrder);

    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrder);

    void beerOrderAllocationFailed(BeerOrderDto beerOrder);
}
