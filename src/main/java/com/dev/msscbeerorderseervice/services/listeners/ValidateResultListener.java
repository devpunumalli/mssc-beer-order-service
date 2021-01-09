package com.dev.msscbeerorderseervice.services.listeners;

import com.dev.brewery.model.events.ValidateOrderResult;
import com.dev.msscbeerorderseervice.config.JmsConfig;
import com.dev.msscbeerorderseervice.services.BeerOrderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateResultListener {
  private final  BeerOrderManager manager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE)
    public  void listen(ValidateOrderResult validateOrderResult){
      final UUID beerOrderId= validateOrderResult.getOrderId();
      log.debug("Validation passed is"+validateOrderResult.getIsValid());
      manager.processValidationResult(beerOrderId, validateOrderResult.getIsValid());
    }
}
