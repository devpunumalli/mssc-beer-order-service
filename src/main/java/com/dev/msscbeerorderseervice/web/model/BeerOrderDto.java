package com.dev.msscbeerorderseervice.web.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;



@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Component
public class BeerOrderDto extends BaseItem {

    @Builder
    public BeerOrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, UUID customerId, List<BeerOrderLineDto> beerOrderLines,
                        OrderStatusEnum orderStatus, String orderStatusCallbackUrl, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.beerOrderLines = beerOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
        this.customerRef = customerRef;
    }
    private UUID customerId;
    private OrderStatusEnum orderStatus;
    private List<BeerOrderLineDto> beerOrderLines;
    private String orderStatusCallbackUrl;
    private String customerRef;
}
