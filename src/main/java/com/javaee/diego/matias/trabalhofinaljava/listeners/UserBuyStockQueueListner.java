package com.javaee.diego.matias.trabalhofinaljava.listeners;

import java.time.LocalDateTime;

import com.javaee.diego.matias.trabalhofinaljava.config.UserBuyStockQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.domain.User;
import com.javaee.diego.matias.trabalhofinaljava.domain.UserBuyStockMessage;
import com.javaee.diego.matias.trabalhofinaljava.repositories.StockRepository;
import com.javaee.diego.matias.trabalhofinaljava.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserBuyStockQueueListner {
  static final Logger logger = LoggerFactory.getLogger(UserBuyStockQueueListner.class);

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private UserRepository userRepository;

  @RabbitListener(queues = UserBuyStockQueueConfig.QUEUE_MESSAGES)
  public void processMessage(UserBuyStockMessage message) {
    User user = userRepository.findById(message.getUser_id()).get();
    Stock stock = stockRepository.findById(message.getStock_id()).get();

    stock.setUser(user);
    stock.setBuyed_at(LocalDateTime.now());
    stock.setSelling(false);

    stockRepository.save(stock);
    logger.info("Buyed company stocks");
  }
}