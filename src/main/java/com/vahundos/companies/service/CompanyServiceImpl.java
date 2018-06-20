package com.vahundos.companies.service;

import com.vahundos.companies.model.Company;
import com.vahundos.companies.repository.CompanyRepository;
import com.vahundos.companies.to.CompanyTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CompanyTo> findAll() {
        return repository.findAll().stream()
                .map(company -> new CompanyTo(company.getId(), company.getName())).collect(Collectors.toList());
    }

    @Override
    public List<CompanyTo> findAllWithChildren() {
        List<Company> companies = repository.findAllWithChildren();

        List<CompanyTo> result = new ArrayList<>();
        fillCompanyToListWithChildren(result, companies.stream()
                .filter(company -> company.getParent() == null).collect(Collectors.toList())); // get only top level companies

        return result;
    }

    private void fillCompanyToListWithChildren(List<CompanyTo> result, List<Company> topLevelCompanies) {
        for (Company company : topLevelCompanies) {
            int earningsWithChildren = company.getChildrenAnnualEarnings();
            CompanyTo companyTo = new CompanyTo(company.getId(), company.getName(), company.getAnnualEarnings(),
                    company.getAnnualEarnings() + earningsWithChildren, new ArrayList<>());

            // fill list of company by children with calculating earnings
            fillCompanyToListWithChildren(companyTo.getChildren(), company.getChildren());

            result.add(companyTo);
        }
    }
}
