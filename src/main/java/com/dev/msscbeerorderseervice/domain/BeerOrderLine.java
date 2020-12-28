package com.dev.msscbeerorderseervice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class BeerOrderLine extends BaseEntity {
    @Builder
    public BeerOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         BeerOrder beerOrder, UUID beerId, Integer orderQuantity,String upc,
                         Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.beerOrder = beerOrder;
        this.beerId = beerId;
        this.orderQuantity = orderQuantity;
        this.upc=upc;
        this.quantityAllocated = quantityAllocated;
    }

    private UUID beerId;
    private Integer orderQuantity;
    private String upc;
    private Integer quantityAllocated;
    @ManyToOne
    private BeerOrder beerOrder;


}
