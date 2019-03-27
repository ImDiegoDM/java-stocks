package com.javaee.diego.matias.trabalhofinaljava.mail;

public interface IEmailService{

  public void sendSimpleMessage(String to, String subject, String text);
}