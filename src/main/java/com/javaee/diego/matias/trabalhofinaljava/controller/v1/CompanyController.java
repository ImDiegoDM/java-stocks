package com.javaee.diego.matias.trabalhofinaljava.controller.v1;

import java.util.Optional;

import javax.validation.Valid;

import com.javaee.diego.matias.trabalhofinaljava.domain.Company;
import com.javaee.diego.matias.trabalhofinaljava.repositories.CompanyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController{
  public static final String BASE_URL = "/api/v1/companies";
  
  @Autowired
  private CompanyRepository repository;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Iterable<Company> findAll(){
    return repository.findAll();
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Company create(@Valid @RequestBody Company user) {
    return repository.save(user);
  }

  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Company findById(@PathVariable Long id) throws ResourceNotFoundException {
    Optional<Company> user = repository.findById(id);
    if(!user.isPresent()){
      throw new ResourceNotFoundException("Company not found");
    }
    
    return user.get();
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    Optional<Company> user = repository.findById(id);
    if(user.isPresent()){
      repository.delete(user.get());
    }
  }

  @PutMapping(path = "/{id}")
  public Company update(@PathVariable Long id, @RequestBody Company user) throws BadHttpRequest {
    if (repository.existsById(id)) {
        user.setId(id);
       return repository.save(user);
    } else {
        throw new BadHttpRequest();
    }
  }
}