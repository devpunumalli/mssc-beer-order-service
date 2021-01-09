package com.dev.brewery.model.events;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Builder
public class ValidateOrderResult {

    private UUID orderId;
    private Boolean isValid;
}
