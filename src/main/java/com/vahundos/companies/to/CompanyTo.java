package com.vahundos.companies.to;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "children")
public class CompanyTo {

    private Integer id;

    private String name;

    private Integer annualEarnings;

    private Integer annualEarningsWithChildren;

    private List<CompanyTo> children;

    public CompanyTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyTo(Integer id, String name, Integer annualEarnings, Integer annualEarningsWithChildren,
                     List<CompanyTo> children) {
        this(id, name);
        this.annualEarnings = annualEarnings;
        this.annualEarningsWithChildren = annualEarningsWithChildren;
        this.children = children;
    }
}
