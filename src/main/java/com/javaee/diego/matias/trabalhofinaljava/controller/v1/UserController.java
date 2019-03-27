package com.javaee.diego.matias.trabalhofinaljava.controller.v1;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.javaee.diego.matias.trabalhofinaljava.domain.SellStockMessage;
import com.javaee.diego.matias.trabalhofinaljava.domain.Stock;
import com.javaee.diego.matias.trabalhofinaljava.domain.User;
import com.javaee.diego.matias.trabalhofinaljava.domain.UserBuyStockMessage;
import com.javaee.diego.matias.trabalhofinaljava.exceptions.StockNotForSaleException;
import com.javaee.diego.matias.trabalhofinaljava.exceptions.StockNotYoursException;
import com.javaee.diego.matias.trabalhofinaljava.repositories.StockRepository;
import com.javaee.diego.matias.trabalhofinaljava.repositories.UserRepository;
import com.javaee.diego.matias.trabalhofinaljava.services.IUserBuyStockQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.tools.web.BadHttpRequest;

@Api("This is User Api")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

  public static final String BASE_URL = "/api/v1/users";

  @Autowired
  private UserRepository repository;

  @Autowired
  private StockRepository stockRepository;

  @Autowired
  private IUserBuyStockQueue queue;

  @ApiOperation(value = "View list of user",notes = "this endpoint will create a user")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<User> findAll(){
    return repository.findAll();
  }

  @ApiOperation(value = "Create user")
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@Valid @RequestBody User user) {
    return repository.save(user);
  }

  @ApiOperation(value = "Finding user by id")
  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User findById(@PathVariable Long id) throws ResourceNotFoundException {
    Optional<User> user = repository.findById(id);
    if(!user.isPresent()){
      throw new ResourceNotFoundException("User not found");
    }
    
    return user.get();
  }

  @ApiOperation(value = "Delete user")
  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    Optional<User> user = repository.findById(id);
    if(user.isPresent()){
      repository.delete(user.get());
    }
  }

  @ApiOperation(value = "Update user")
  @PutMapping(path = "/{id}",consumes = "application/json")
  public User update(@PathVariable Long id,@Valid @RequestBody User user) throws ResourceNotFoundException {
    if (repository.existsById(id)) {
        user.setId(id);
       return repository.save(user);
    } else {
        throw new ResourceNotFoundException("User not found");
    }
  }

  @ApiOperation(value = "Sell user stock")
  @PostMapping(path = "/{id}/sell",consumes = "application/json")
  public Stock sellStock(@PathVariable Long id,@Valid @RequestBody SellStockMessage message) throws ResourceNotFoundException,BadHttpRequest {
    Optional<User> user = repository.findById(id);
    Optional<Stock> stock = stockRepository.findById(message.getStock_id());
    if (user.isPresent() && stock.isPresent()){
      List<Stock> list = user.get().getStocks();
      boolean finded=false;
      Stock s = stock.get();

      for (Stock var : list) {
        if(var.getId() == s.getId()){
          finded=true;
        }  
      }

      if(!finded){
        throw new StockNotYoursException();
      }

      s.setValue(message.getValue());
      s.setSelling(true);
      return stockRepository.save(s);
    }

    throw new ResourceNotFoundException("User not found");
  }

  @ApiOperation(value = "Buy stock")
  @PostMapping(path = "/{id}/buy",consumes = "application/json")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public String buyStock(@PathVariable Long id,@Valid @RequestBody UserBuyStockMessage message) throws ResourceNotFoundException  {
    if (repository.existsById(id)) {
      Optional<Stock> s = stockRepository.findById(message.getStock_id());
      if(s.isPresent()){
        if(s.get().getSelling() == true){
          message.setUser_id(id);
          queue.sendMessage(message);
  
          return "Buying your stocks";
        }

        throw new StockNotForSaleException();
      }

      throw new ResourceNotFoundException("Stock not found");
    }

    throw new ResourceNotFoundException("User not found");
  }
}