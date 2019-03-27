package com.javaee.diego.matias.trabalhofinaljava.listeners;

import java.time.LocalDateTime;

import com.javaee.diego.matias.trabalhofinaljava.config.UserBuyStockQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.domain.User;
import com.javaee.diego.matias.trabalhofinaljava.domain.UserBuyStockMessage;
import com.javaee.diego.matias.trabalhofinaljava.mail.IEmailService;
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

  @Autowired
  private IEmailService mail;

  @RabbitListener(queues = UserBuyStockQueueConfig.QUEUE_MESSAGES)
  public void processMessage(UserBuyStockMessage message) {
    User user = userRepository.findById(message.getUser_id()).get();
    Stock stock = stockRepository.findById(message.getStock_id()).get();

    User oldUser = stock.getUser();
    if(oldUser != null){
      mail.sendSimpleMessage(oldUser.getEmail(), "Selled stock", "you just sell one of your stocks");
    }

    stock.setUser(user);
    stock.setBuyed_at(LocalDateTime.now());
    stock.setSelling(false);

    mail.sendSimpleMessage(user.getEmail(), "Buyed stock", "you just buy a stock");
    mail.sendSimpleMessage(stock.getCompany().getEmail(), "Stock seled", "one of you stocks has ben selled");

    stockRepository.save(stock);
    logger.info("Buyed company stocks");
  }
}