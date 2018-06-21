package com.vahundos.companies.web;

import com.vahundos.companies.service.CompanyService;
import com.vahundos.companies.to.CompanyTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = CompanyAjaxController.AJAX_URL)
public class CompanyAjaxController {

    public static final String AJAX_URL = "/ajax/companies";

    private final CompanyService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyAjaxController.class);

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
        LOGGER.debug("find all companies");
        return service.findAll();
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CompanyTo> findAllWithChildren() {
        LOGGER.debug("find all companies with children");
        return service.findAllWithChildren();
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable int id) {
        LOGGER.debug("delete company with id = {}", id);
        service.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@Valid @RequestBody CompanyTo company) {
        LOGGER.debug("create new company {}", company);
        service.create(company);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody CompanyTo company) {
        LOGGER.debug("update company {}", company);
        service.update(company);
    }
}
