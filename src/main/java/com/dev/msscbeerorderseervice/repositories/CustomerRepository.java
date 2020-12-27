package com.dev.msscbeerorderseervice.repositories;

import com.dev.msscbeerorderseervice.domain.BeerOrder;
import com.dev.msscbeerorderseervice.domain.Customer;
import com.dev.msscbeerorderseervice.web.model.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
