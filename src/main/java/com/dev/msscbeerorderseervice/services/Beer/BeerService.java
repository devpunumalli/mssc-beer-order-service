package com.dev.msscbeerorderseervice.services.Beer;

import com.dev.msscbeerorderseervice.services.Beer.model.BeerDto;

import java.util.Optional;
import java.util.UUID;

public interface BeerService {

   Optional<BeerDto> getBeerById(UUID id);
    Optional<BeerDto> getBeerByUpc(String upc);
}
