package com.vahundos.companies.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyAjaxController.class);

    @RequestMapping("/")
    public String mainPage() {
        LOGGER.debug("request for root");
        return "main";
    }
}
