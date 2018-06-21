package com.vahundos.companies.service;

import com.vahundos.companies.model.Company;
import com.vahundos.companies.repository.CompanyRepository;
import com.vahundos.companies.to.CompanyTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.vahundos.companies.util.CompanyUtils.*;

@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompanyTo findById(int id) {
        return createCompanyToFromEntity(repository.findById(id).get());
    }

    @Override
    @Cacheable("companies")
    public List<CompanyTo> findAll() {
        return repository.findAll().stream()
                .map(company -> new CompanyTo(company.getId(), company.getName())).collect(Collectors.toList());
    }

    @Override
    @Cacheable("companiesWithChildren")
    public List<CompanyTo> findAllWithChildren() {
        List<Company> companies = repository.findAllWithChildren();

        List<CompanyTo> result = new ArrayList<>();
        fillCompanyToListWithChildren(result, companies.stream()
                .filter(company -> company.getParent() == null).collect(Collectors.toList())); // get only top level companies

        return result;
    }

    @Override
    @Transactional
    @CacheEvict(value = {"companies", "companiesWithChildren"}, allEntries = true)
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = {"companies", "companiesWithChildren"}, allEntries = true)
    public Company create(CompanyTo company) {
        if (company.getId() != null) {
            throw new IllegalStateException("Can't create company with existing id = " + company.getId());
        }

        return repository.save(createCompanyFromTo(company, repository.getOneOrNull(company.getParentId())));
    }

    @Override
    @Transactional
    @CacheEvict(value = {"companies", "companiesWithChildren"}, allEntries = true)
    public void update(CompanyTo company) {
        if (company.getId() == null) {
            throw new IllegalStateException("Can't update company with null id");
        }

        Company companyFromTo = createCompanyFromTo(company, repository.getOneOrNull(company.getParentId()));
        companyFromTo.setChildren(repository.findById(company.getId()).get().getChildren());
        repository.save(companyFromTo);
    }
}
