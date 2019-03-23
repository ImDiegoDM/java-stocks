package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.domain.UserBuyStockMessage;

public interface IUserBuyStockQueue {
  public void sendMessage(UserBuyStockMessage message);
}