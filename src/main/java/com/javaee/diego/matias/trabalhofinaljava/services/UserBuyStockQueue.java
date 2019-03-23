package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.config.UserBuyStockQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.UserBuyStockMessage;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserBuyStockQueue implements IUserBuyStockQueue {

  private final RabbitTemplate rabbitTemplate;

  public UserBuyStockQueue(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @Override
  public void sendMessage(UserBuyStockMessage message) {
    this.rabbitTemplate.convertAndSend(UserBuyStockQueueConfig.QUEUE_MESSAGES, message);
  }

}