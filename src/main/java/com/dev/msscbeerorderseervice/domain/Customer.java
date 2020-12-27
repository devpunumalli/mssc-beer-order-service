package com.dev.msscbeerorderseervice.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;


@Entity
@Setter
@Getter
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String customerName;

    @Id
    private UUID apiKey;

    @OneToMany(mappedBy = "customer")
    private Set<BeerOrder> beerOrders;
}
