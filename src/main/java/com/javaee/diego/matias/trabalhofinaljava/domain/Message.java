package com.javaee.diego.matias.trabalhofinaljava.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Message implements Serializable{
  private static final long serialVersionUID = 1L;
	
	private String subject;
	private String body;
}