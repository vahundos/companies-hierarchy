package com.vahundos.companies.util;

import com.vahundos.companies.model.Company;
import com.vahundos.companies.to.CompanyTo;

import java.util.ArrayList;
import java.util.List;

public class CompanyUtils {

    public static Company createCompanyFromTo(CompanyTo companyTo, Company parent) {
        return new Company(companyTo.getId(), companyTo.getName(), companyTo.getAnnualEarnings(), parent);
    }

    public static CompanyTo createCompanyToFromEntity(Company company) {
        return new CompanyTo(company.getId(), company.getName(), company.getAnnualEarnings());
    }

    public static void fillCompanyToListWithChildren(List<CompanyTo> result, List<Company> topLevelCompanies) {
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
