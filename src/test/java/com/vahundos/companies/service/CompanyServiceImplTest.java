package com.vahundos.companies.service;

import com.vahundos.companies.AbstractTest;
import com.vahundos.companies.model.Company;
import com.vahundos.companies.to.CompanyTo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

import static com.vahundos.companies.CompanyTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CompanyServiceImplTest extends AbstractTest {

    @Autowired
    private CompanyService service;

    @Test
    public void testFindById() {
        CompanyTo company = service.findById(COMPANY_WITH_CHILDREN_TO1.getId());

        assertMatch(COMPANY_WITH_CHILDREN_TO1, company);
    }

    @Test
    public void testFindAll() {
        List<CompanyTo> companies = service.findAll();

        assertThat(companies.size()).isEqualTo(7);
        assertThat(companies).usingFieldByFieldElementComparator().containsExactly(COMPANY_TO1, COMPANY_TO2, COMPANY_TO3,
                COMPANY_TO4, COMPANY_TO5, COMPANY_TO6, COMPANY_TO7);
    }

    @Test
    public void testFindAllWithChildren() {
        List<CompanyTo> companiesWithChildren = service.findAllWithChildren();

        assertThat(companiesWithChildren.size()).isEqualTo(2);
        assertThat(companiesWithChildren).usingElementComparatorIgnoringFields("parentId", "children")
                .containsExactlyInAnyOrder(COMPANY_WITH_CHILDREN_TO1, COMPANY_WITH_CHILDREN_TO5);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDelete() {
        service.delete(COMPANY_TO1.getId());
        service.findById(COMPANY_TO1.getId());
    }

    @Test
    public void testCreate() {
        CompanyTo forCreation = getForCreation();
        Company createdCompany = service.create(forCreation);
        forCreation.setId(createdCompany.getId());

        CompanyTo actuallyCreatedTo = service.findById(forCreation.getId());
        assertMatch(forCreation, actuallyCreatedTo);
    }

    @Test
    public void testUpdate() {
        CompanyTo forUpdate = getForUpdate();

        service.update(forUpdate);
        CompanyTo actuallyUpdated = service.findById(forUpdate.getId());

        assertMatch(forUpdate, actuallyUpdated);
    }
}