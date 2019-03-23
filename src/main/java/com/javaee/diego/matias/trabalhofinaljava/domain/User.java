package com.javaee.diego.matias.trabalhofinaljava.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @NotBlank
  @Size(min = 3,max = 255)
  private String name;

  @NotBlank
  @Size(min = 11,max = 11)
  private String cpf;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  private Set<Stock> stocks = new HashSet<>();
}