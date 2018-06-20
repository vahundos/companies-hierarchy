package com.vahundos.companies.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "children")
public class CompanyTo {

    private Integer id;

    private String name;

    private Integer annualEarnings;

    private Integer annualEarningsWithChildren;

    private Integer parentId;

    private List<CompanyTo> children;

    public CompanyTo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public CompanyTo(Integer id, String name, Integer annualEarnings) {
        this(id, name);
        this.annualEarnings = annualEarnings;
    }

    public CompanyTo(Integer id, String name, Integer annualEarnings, Integer annualEarningsWithChildren,
                     List<CompanyTo> children) {
        this(id, name, annualEarnings);
        this.annualEarningsWithChildren = annualEarningsWithChildren;
        this.children = children;
    }
}
