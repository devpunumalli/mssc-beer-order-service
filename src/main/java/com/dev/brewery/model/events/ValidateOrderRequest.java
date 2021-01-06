package com.dev.brewery.model.events;

import com.dev.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ValidateOrderRequest
{
    private BeerOrderDto beerOrderDto;
}
