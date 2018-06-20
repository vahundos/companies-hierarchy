package com.vahundos.companies.service;

import com.vahundos.companies.to.CompanyTo;

import java.util.List;

public interface CompanyService {

    List<CompanyTo> findAll();

    List<CompanyTo> findAllWithChildren();
}
