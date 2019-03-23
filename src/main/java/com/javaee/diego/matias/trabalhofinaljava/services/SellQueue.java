package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.config.SellQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SellQueue implements ISellQueue {

  private final RabbitTemplate rabbitTemplate;

  public SellQueue(RabbitTemplate rabbitTemplate){
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void sendMessage(Message message) {
    this.rabbitTemplate.convertAndSend(SellQueueConfig.QUEUE_MESSAGES, message);
  }

}