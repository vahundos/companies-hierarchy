package com.vahundos.companies.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = "children")
public class CompanyTo {

    private Integer id;

    @Size(min = 3, max = 255, message = "size must be between  3 and 255")
    private String name;

    @NotNull(message = "must not be blank")
    @Positive(message = "must be greater than 0")
    @Max(value = 100000, message = "must be less than or equal to 100000")
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
