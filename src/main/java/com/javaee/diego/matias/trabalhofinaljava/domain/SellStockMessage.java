package com.javaee.diego.matias.trabalhofinaljava.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class SellStockMessage{

	@NotNull
	private Long stock_id;

	@NotNull
	@Positive
	private Float value;
}