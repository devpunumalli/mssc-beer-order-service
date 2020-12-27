package com.dev.msscbeerorderseervice.repositories;

import com.dev.msscbeerorderseervice.domain.BeerOrder;
import com.dev.msscbeerorderseervice.domain.BeerOrderLine;
import com.dev.msscbeerorderseervice.web.model.BeerOrderLineDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerOrderLineRepository extends JpaRepository<BeerOrderLine, UUID> {
}
