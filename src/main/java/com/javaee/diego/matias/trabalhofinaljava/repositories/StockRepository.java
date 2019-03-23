package com.javaee.diego.matias.trabalhofinaljava.repositories;

import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface StockRepository extends JpaRepository<Stock, Long>{

}