package com.javaee.diego.matias.trabalhofinaljava.repositories;

import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, Long>{

}