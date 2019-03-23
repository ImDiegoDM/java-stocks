package com.javaee.diego.matias.trabalhofinaljava.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.repositories.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(StockController.BASE_URL)
public class StockController{
  public static final String BASE_URL = "/api/v1/stocks";

  @Autowired
  private StockRepository repository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Iterable<Stock> findAll(){
    return repository.findAll();
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Stock getOne(@PathVariable Long id){
    Optional<Stock> stock = repository.findById(id);
    if(stock.isPresent()){
      return stock.get();
    }
    throw new ResourceNotFoundException("Stock not found");
  }

  @GetMapping(path = "/selling")
  @ResponseStatus(HttpStatus.OK)
  public Iterable<Stock> findSelingAll(){
    return repository.findBySelling(true);
  }
} 