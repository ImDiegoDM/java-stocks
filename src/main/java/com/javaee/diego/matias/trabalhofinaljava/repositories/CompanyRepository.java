package com.javaee.diego.matias.trabalhofinaljava.repositories;

import com.javaee.diego.matias.trabalhofinaljava.domain.Company;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long>{

}