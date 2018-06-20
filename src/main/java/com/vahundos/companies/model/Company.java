package com.vahundos.companies.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    private Integer id;

    private String name;

    private int annualEarnings;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Company parent;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Company> children;

    public Company(String name, int annualEarnings, Company parent) {
        this.name = name;
        this.annualEarnings = annualEarnings;
        this.parent = parent;
    }

    public Company(String name, int annualEarnings) {
        this(name, annualEarnings, null);
    }

    // children annual earnings without own earnings
    public int getChildrenAnnualEarnings() {
        int result = 0;
        for (Company child : children) {
            result += child.annualEarnings + child.getChildrenAnnualEarnings();
        }
        return result;
    }
}
