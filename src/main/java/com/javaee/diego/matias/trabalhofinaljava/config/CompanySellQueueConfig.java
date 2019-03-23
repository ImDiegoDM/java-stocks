package com.javaee.diego.matias.trabalhofinaljava.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompanySellQueueConfig {
  public static final String QUEUE_MESSAGES = "companysell-queue";
  public static final String EXCHANGE_MESSAGES = "companysell-exchange";
  public static final String QUEUE_DEAD_MESSAGES = "dead-companysell-queue";

  @Bean
  Queue sellMessagesQueue() {
    return QueueBuilder.durable(QUEUE_MESSAGES).withArgument("x-dead-letter-exchange", "")
        .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
        .withArgument("x-message-ttl", 15000)
        .build();
  }

  @Bean
  Queue deadSellMessagesQueue() {
    return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
  }

  @Bean
  Exchange sellMessagesExchange() {
    return ExchangeBuilder.topicExchange(EXCHANGE_MESSAGES).build();
  }
}