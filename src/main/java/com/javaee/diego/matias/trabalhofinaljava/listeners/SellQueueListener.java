package com.javaee.diego.matias.trabalhofinaljava.listeners;

import com.javaee.diego.matias.trabalhofinaljava.config.SellQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SellQueueListener{
  static final Logger logger = LoggerFactory.getLogger(SellQueueListener.class);

  @RabbitListener(queues = SellQueueConfig.QUEUE_MESSAGES)
  public void processMessage(Message message) {
      logger.info("Message Received");
      logger.info("Sebject:" + message.getSubject());
      logger.info("Body:" + message.getBody());
  }
}