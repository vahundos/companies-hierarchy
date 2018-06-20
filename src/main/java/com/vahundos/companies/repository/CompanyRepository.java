package com.vahundos.companies.repository;

import com.vahundos.companies.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT DISTINCT c FROM Company c LEFT JOIN FETCH c.children ORDER BY c.name")
    List<Company> findAllWithChildren();

    default Company getOneOrNull(Integer id) {
        return id == null ? null : getOne(id);
    }
}
