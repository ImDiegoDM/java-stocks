package com.javaee.diego.matias.trabalhofinaljava.listeners;

import com.javaee.diego.matias.trabalhofinaljava.config.CompanySellQueueConfig;
import com.javaee.diego.matias.trabalhofinaljava.domain.Company;
import com.javaee.diego.matias.trabalhofinaljava.domain.CompanySellMessage;
import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.mail.IEmailService;
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

  @Autowired
  private IEmailService mail;

  @RabbitListener(queues = CompanySellQueueConfig.QUEUE_MESSAGES)
  public void processMessage(CompanySellMessage message) {
    Company comp = companyRepository.findById(message.getCompany_id()).get();
    for (int i = 0; i < message.getQuantity(); i++) {
      Stock s = new Stock();
      s.setCompany(comp);
      s.setInitialValue(message.getValue());
      s.setValue(message.getValue());
      s.setSelling(true);
      stockRepository.save(s);
    }

    mail.sendSimpleMessage(comp.getEmail(), "Created stocks", "you just create "+message.getQuantity().toString()+" stock(s)");
    logger.info("Created company stocks");
  }
}