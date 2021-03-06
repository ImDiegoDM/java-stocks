package com.javaee.diego.matias.trabalhofinaljava.controller.v1;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.javaee.diego.matias.trabalhofinaljava.domain.Company;
import com.javaee.diego.matias.trabalhofinaljava.domain.CompanySellMessage;
import com.javaee.diego.matias.trabalhofinaljava.repositories.CompanyRepository;
import com.javaee.diego.matias.trabalhofinaljava.services.ICompanySellQueue;

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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("This is Company Api")
@RestController
@RequestMapping(CompanyController.BASE_URL)
public class CompanyController{
  public static final String BASE_URL = "/api/v1/companies";
  
  @Autowired
  private CompanyRepository repository;

  @Autowired
  private ICompanySellQueue companySellQueue;

  @ApiOperation(value = "View all companies")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Company> findAll(){
    return repository.findAll();
  }

  @ApiOperation(value = "Create a company")
  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public Company create(@Valid @RequestBody Company user) {
    return repository.save(user);
  }

  @ApiOperation(value = "Get one company")
  @GetMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Company findById(@PathVariable Long id) throws ResourceNotFoundException {
    Optional<Company> user = repository.findById(id);
    if(!user.isPresent()){
      throw new ResourceNotFoundException("company not found");
    }
    
    return user.get();
  }

  @ApiOperation(value = "Delete a company")
  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    Optional<Company> user = repository.findById(id);
    if(user.isPresent()){
      repository.delete(user.get());
    }
  }

  @ApiOperation(value = "Update a company")
  @PutMapping(path = "/{id}",consumes = "application/json")
  public Company update(@PathVariable Long id,@Valid @RequestBody Company user) throws ResourceNotFoundException {
    if (repository.existsById(id)) {
        user.setId(id);
       return repository.save(user);
    } else {
        throw new ResourceNotFoundException("company not found");
    }
  }

  @ApiOperation(value = "Create stocks for this company")
  @PostMapping(path = "/{id}/stocks",consumes = "application/json")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public String putToSell(@PathVariable Long id,@Valid @RequestBody CompanySellMessage message) throws ResourceNotFoundException{
    if (repository.existsById(id)) {
      message.setCompany_id(id);
      companySellQueue.sendMessage(message);
      return "Creating your stocks";
    } else {
      throw new ResourceNotFoundException("company not found");
    }
  }
}