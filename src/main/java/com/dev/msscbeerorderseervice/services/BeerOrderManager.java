package com.dev.msscbeerorderseervice.services;

import com.dev.msscbeerorderseervice.domain.BeerOrder;

public interface BeerOrderManager {

    BeerOrder newOrder(BeerOrder beerOrder);
}
