package com.javaee.diego.matias.trabalhofinaljava.repositories;

import java.util.List;

import com.javaee.diego.matias.trabalhofinaljava.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long>{
  
}