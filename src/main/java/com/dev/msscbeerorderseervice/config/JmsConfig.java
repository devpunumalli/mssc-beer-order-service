package com.dev.msscbeerorderseervice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {


    public static final String VALIDATE_ORDER_QUEUE="validate-order";
    public static final String VALIDATE_ORDER_RESPONSE = "validate-order-response";
    public static final String ALLOCATE_ORDER = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE = "allocate-order-response";


    @Bean
    public MessageConverter jacksonMessageConverter(){
        MappingJackson2MessageConverter converter=new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");

        return converter;
    }
}
