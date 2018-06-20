package com.vahundos.companies.service;

import com.vahundos.companies.to.CompanyTo;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.vahundos.companies.CompanyTestData.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql({"/db/init_db.sql", "/db/populate_db.sql"})
public class CompanyServiceImplTest {

    @Autowired
    private CompanyService service;

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
}