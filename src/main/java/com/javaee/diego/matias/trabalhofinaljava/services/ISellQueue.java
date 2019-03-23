package com.javaee.diego.matias.trabalhofinaljava.services;

import com.javaee.diego.matias.trabalhofinaljava.domain.Message;

public interface ISellQueue{
  public void sendMessage(Message message);
}