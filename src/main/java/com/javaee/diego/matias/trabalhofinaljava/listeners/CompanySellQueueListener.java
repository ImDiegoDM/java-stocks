package com.javaee.diego.matias.trabalhofinaljava.listeners;

import java.time.LocalDateTime;

import com.javaee.diego.matias.trabalhofinaljava.config.CompanySellQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Company;
import com.javaee.diego.matias.trabalhofinaljava.domain.CompanySellMessage;
import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.repositories.CompanyRepository;
import com.javaee.diego.matias.trabalhofinaljava.repositories.StockRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanySellQueueListener{
  static final Logger logger = LoggerFactory.getLogger(CompanySellQueueListener.class);

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private CompanyRepository companyRepository;

  @RabbitListener(queues = CompanySellQueueConfig.QUEUE_MESSAGES)
  public void processMessage(CompanySellMessage message) {
    for (int i = 0; i < message.getQuantity(); i++) {
      Stock s = new Stock();
      s.setBuyed_at(LocalDateTime.now());
      Company comp = companyRepository.findById(message.getCompany_id()).get();
      s.setCompany(comp);
      s.setInitialValue(message.getValue());
      s.setValue(message.getValue());
      s.setSelling(true);
      stockRepository.save(s);
    }
    logger.info("Created company stocks");
  }
}