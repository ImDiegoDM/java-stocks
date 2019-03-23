package com.javaee.diego.matias.trabalhofinaljava.services;

import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
  @Override
  public String test(){
    return "worked";
  }
}