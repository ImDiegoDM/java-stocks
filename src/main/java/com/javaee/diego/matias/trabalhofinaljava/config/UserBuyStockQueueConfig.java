package com.javaee.diego.matias.trabalhofinaljava.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBuyStockQueueConfig {
  public static final String QUEUE_MESSAGES = "userbuy-queue";
  public static final String EXCHANGE_MESSAGES = "userbuy-exchange";
  public static final String QUEUE_DEAD_MESSAGES = "dead-userbuy-queue";

  @Bean
  Queue userBuyStockQueueBuild() {
    return QueueBuilder.durable(QUEUE_MESSAGES).withArgument("x-dead-letter-exchange", "")
        .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
        .withArgument("x-message-ttl", 15000)
        .build();
  }

  @Bean
  Queue deadUserBuyStockQueueBuild() {
    return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
  }

  @Bean
  Exchange userBuyStockExchangeBuild() {
    return ExchangeBuilder.topicExchange(EXCHANGE_MESSAGES).build();
  }
}