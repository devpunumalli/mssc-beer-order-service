package com.dev.brewery.model.events;

import com.dev.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data

public class AllocationOrderResult {
    private BeerOrderDto beerOrderDto;
    private Boolean allocationError= false;
    private Boolean pendingInventory=false;
}
