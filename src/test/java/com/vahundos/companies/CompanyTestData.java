package com.vahundos.companies;

import com.vahundos.companies.to.CompanyTo;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyTestData {

    public static final CompanyTo COMPANY_TO1 = new CompanyTo(1, "Company1");
    public static final CompanyTo COMPANY_TO2 = new CompanyTo(2, "Company2");
    public static final CompanyTo COMPANY_TO3 = new CompanyTo(3, "Company3");
    public static final CompanyTo COMPANY_TO4 = new CompanyTo(4, "Company4");
    public static final CompanyTo COMPANY_TO5 = new CompanyTo(5, "Company5");
    public static final CompanyTo COMPANY_TO6 = new CompanyTo(6, "Company6");
    public static final CompanyTo COMPANY_TO7 = new CompanyTo(7, "Company7");

    public static final CompanyTo COMPANY_WITH_CHILDREN_TO1 = new CompanyTo(1, "Company1", 25, 53, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO2 = new CompanyTo(2, "Company2", 13, 18, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO3 = new CompanyTo(3, "Company3", 5, 5, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO4 = new CompanyTo(4, "Company4", 10, 10, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO5 = new CompanyTo(5, "Company5", 10, 30, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO6 = new CompanyTo(6, "Company6", 15, 15, new ArrayList<>());
    public static final CompanyTo COMPANY_WITH_CHILDREN_TO7 = new CompanyTo(7, "Company7", 5, 5, new ArrayList<>());

    static {
        COMPANY_WITH_CHILDREN_TO1.getChildren().add(COMPANY_WITH_CHILDREN_TO2);
        COMPANY_WITH_CHILDREN_TO1.getChildren().add(COMPANY_WITH_CHILDREN_TO4);

        COMPANY_WITH_CHILDREN_TO2.getChildren().add(COMPANY_WITH_CHILDREN_TO3);

        COMPANY_WITH_CHILDREN_TO5.getChildren().add(COMPANY_WITH_CHILDREN_TO6);
        COMPANY_WITH_CHILDREN_TO5.getChildren().add(COMPANY_WITH_CHILDREN_TO7);
    }

    public static void assertMatch(CompanyTo expected, CompanyTo actually) {
        assertThat(actually).isEqualToIgnoringGivenFields(expected, "annualEarningsWithChildren", "parentId", "children");
    }
}
