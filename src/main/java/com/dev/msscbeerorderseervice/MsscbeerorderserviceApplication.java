package com.dev.msscbeerorderseervice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = ArtemisAutoConfiguration.class)
public class MsscbeerorderserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsscbeerorderserviceApplication.class, args);
    }

}
