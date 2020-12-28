package com.dev.msscbeerorderseervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.dev.msscbeerorderseervice")
public class MsscbeerorderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscbeerorderserviceApplication.class, args);
    }

}
