package com.javaee.diego.matias.trabalhofinaljava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This stock is not for sale")
public class StockNotForSaleException extends RuntimeException {}