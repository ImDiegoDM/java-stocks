package com.javaee.diego.matias.trabalhofinaljava.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserBuyStockMessage implements Serializable {
  private static final long serialVersionUID = 1L;
	
  private Long user_id;
  
  @NotNull
  private Long stock_id;
}