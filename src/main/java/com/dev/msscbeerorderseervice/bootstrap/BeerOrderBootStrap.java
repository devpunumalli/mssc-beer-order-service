package com.dev.msscbeerorderseervice.bootstrap;

import com.dev.msscbeerorderseervice.domain.Customer;
import com.dev.msscbeerorderseervice.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BeerOrderBootStrap implements CommandLineRunner {
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final CustomerRepository customerRepository;

    public BeerOrderBootStrap(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            Customer savedCutomer = customerRepository.save(Customer.builder()
                                                            .customerName(TASTING_ROOM)
                                                            .apiKey(UUID.randomUUID()).build());

           log.debug("Tasting Room Cutomer Id is" + savedCutomer.getId() );

        }
    }
}
