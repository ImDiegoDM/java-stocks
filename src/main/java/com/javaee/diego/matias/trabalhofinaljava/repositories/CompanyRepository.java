package com.javaee.diego.matias.trabalhofinaljava.repositories;

import com.javaee.diego.matias.trabalhofinaljava.domain.Company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface CompanyRepository extends JpaRepository<Company, Long>{

}