package com.javaee.diego.matias.trabalhofinaljava.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class CompanySellMessage implements Serializable{
  private static final long serialVersionUID = 1L;
	
	private Long company_id;

	@NotNull
	@Positive
	private Long quantity;

	@NotNull
	@Positive
	private Float value;
}