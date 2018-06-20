package com.vahundos.companies.service;

import com.vahundos.companies.model.Company;
import com.vahundos.companies.to.CompanyTo;

import java.util.List;

public interface CompanyService {

    CompanyTo findById(int id);

    List<CompanyTo> findAll();

    List<CompanyTo> findAllWithChildren();

    void delete(int id);

    Company create(CompanyTo company);

    void update(CompanyTo company);
}
