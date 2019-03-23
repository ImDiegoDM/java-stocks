package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.config.CompanySellQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.CompanySellMessage;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompanySellQueue implements ICompanySellQueue {

  private final RabbitTemplate rabbitTemplate;

  public CompanySellQueue(RabbitTemplate rabbitTemplate){
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void sendMessage(CompanySellMessage message) {
    this.rabbitTemplate.convertAndSend(CompanySellQueueConfig.QUEUE_MESSAGES, message);
  }

}