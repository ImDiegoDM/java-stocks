package com.javaee.diego.matias.trabalhofinaljava.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Stock{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnoreProperties("stocks")
  private Company company;

  @ManyToOne
  @JsonIgnoreProperties("stocks")
  private User user;

  private Float value;

  private Float initialValue;

  private LocalDateTime buyed_at;
}