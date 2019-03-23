package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.domain.CompanySellMessage;

public interface ICompanySellQueue{
  public void sendMessage(CompanySellMessage message);
}