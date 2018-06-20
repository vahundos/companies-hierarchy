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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer annualEarnings;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Company parent;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parent_id")
    private List<Company> children;

    public Company(Integer id, String name, Integer annualEarnings, Company parent) {
        this(name, annualEarnings, parent);
        this.id = id;
    }

    public Company(String name, Integer annualEarnings, Company parent) {
        this.name = name;
        this.annualEarnings = annualEarnings;
        this.parent = parent;
    }

    public Company(String name, Integer annualEarnings) {
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
