package com.dev.msscbeerorderseervice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;


@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Component
public class BeerOrderLineDto extends BaseItem {

    @Builder
    public BeerOrderLineDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate,
                            String upc, String beerName, String beerStyle,BigDecimal price,UUID beerId, Integer orderQuantity) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.beerName = beerName;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.price=price;
                this.beerStyle=beerStyle;
    }

    private String upc;
    private String beerName;
    private UUID beerId;
    private Integer orderQuantity;
private String beerStyle;
private BigDecimal price;
}
