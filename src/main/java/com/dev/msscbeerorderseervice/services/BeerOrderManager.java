package com.dev.msscbeerorderseervice.services;

import com.dev.msscbeerorderseervice.domain.BeerOrder;

import java.util.UUID;

public interface BeerOrderManager {

    BeerOrder newOrder(BeerOrder beerOrder);

    void processValidationResult(UUID beerOrderId, Boolean isValid);
}
