package com.vahundos.companies.web;

import com.vahundos.companies.service.CompanyService;
import com.vahundos.companies.to.CompanyTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = CompanyAjaxController.AJAX_URL)
public class CompanyAjaxController {

    public static final String AJAX_URL = "/ajax/companies";

    private final CompanyService service;

    @Autowired
    public CompanyAjaxController(CompanyService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CompanyTo findById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyTo> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyTo> findAllWithChildren() {
        return service.findAllWithChildren();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CompanyTo company) {
        service.create(company);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody CompanyTo company) {
        service.update(company);
    }
}
