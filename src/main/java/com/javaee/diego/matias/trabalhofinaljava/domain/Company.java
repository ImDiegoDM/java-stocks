package com.javaee.diego.matias.trabalhofinaljava.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(min = 2,max = 255)
  private String name;

  @NotBlank
  @Email
  private String email;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
  @JsonIgnoreProperties("company")
  private List<Stock> stocks;
}