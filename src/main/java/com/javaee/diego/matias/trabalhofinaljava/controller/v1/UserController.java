package com.javaee.diego.matias.trabalhofinaljava.controller.v1;

import java.util.Optional;

import javax.validation.Valid;

import com.javaee.diego.matias.trabalhofinaljava.domain.User;
import com.javaee.diego.matias.trabalhofinaljava.repositories.UserRepository;

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
import javassist.tools.web.BadHttpRequest;

@Api("This is User Api")
@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

  public static final String BASE_URL = "/api/v1/users";

  @Autowired
  private UserRepository repository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Iterable<User> findAll(){
    return repository.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@Valid @RequestBody User user) {
    return repository.save(user);
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public User findById(@PathVariable Long id) throws ResourceNotFoundException {
    Optional<User> user = repository.findById(id);
    if(!user.isPresent()){
      throw new ResourceNotFoundException("User not found");
    }
    
    return user.get();
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    Optional<User> user = repository.findById(id);
    if(user.isPresent()){
      repository.delete(user.get());
    }
  }

  @PutMapping(path = "/{id}")
  public User update(@PathVariable Long id, @RequestBody User user) throws BadHttpRequest {
    if (repository.existsById(id)) {
        user.setId(id);
       return repository.save(user);
    } else {
        throw new BadHttpRequest();
    }
  }
}