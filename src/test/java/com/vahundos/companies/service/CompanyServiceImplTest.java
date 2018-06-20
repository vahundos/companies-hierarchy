package com.vahundos.companies.service;

import com.vahundos.companies.model.Company;
import com.vahundos.companies.to.CompanyTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.NoSuchElementException;

import static com.vahundos.companies.CompanyTestData.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"/db/init_db.sql", "/db/populate_db.sql"})
public class CompanyServiceImplTest {

    @Autowired
    private CompanyService service;

    @Test
    public void findById() {
        CompanyTo company = service.findById(COMPANY_WITH_CHILDREN_TO1.getId());

        assertMatch(COMPANY_WITH_CHILDREN_TO1, company);
    }

    @Test
    public void findAll() {
        List<CompanyTo> companies = service.findAll();

        assertThat(companies.size()).isEqualTo(7);
        assertThat(companies).usingFieldByFieldElementComparator().containsExactly(COMPANY_TO1, COMPANY_TO2, COMPANY_TO3,
                COMPANY_TO4, COMPANY_TO5, COMPANY_TO6, COMPANY_TO7);
    }

    @Test
    public void findAllWithChildren() {
        List<CompanyTo> companiesWithChildren = service.findAllWithChildren();

        assertThat(companiesWithChildren.size()).isEqualTo(2);
        assertThat(companiesWithChildren).usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(COMPANY_WITH_CHILDREN_TO1, COMPANY_WITH_CHILDREN_TO5);
    }

    @Test(expected = NoSuchElementException.class)
    public void delete() {
        service.delete(COMPANY_TO1.getId());
        service.findById(COMPANY_TO1.getId());
    }

    @Test
    public void create() {
        CompanyTo forCreation = new CompanyTo(null, "created", 250);
        Company createdCompany = service.create(forCreation);
        forCreation.setId(createdCompany.getId());

        CompanyTo actuallyCreatedTo = service.findById(forCreation.getId());
        assertMatch(forCreation, actuallyCreatedTo);
    }

    @Test
    public void update() {
        CompanyTo forUpdate = new CompanyTo(COMPANY_WITH_CHILDREN_TO1.getId(), COMPANY_WITH_CHILDREN_TO1.getName(),
                COMPANY_WITH_CHILDREN_TO1.getAnnualEarnings());

        service.update(forUpdate);
        CompanyTo actuallyUpdated = service.findById(forUpdate.getId());

        assertMatch(forUpdate, actuallyUpdated);
    }
}