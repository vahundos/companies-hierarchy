package com.vahundos.companies.web;

import com.vahundos.companies.AbstractTest;
import com.vahundos.companies.to.CompanyTo;
import com.vahundos.companies.util.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static com.vahundos.companies.CompanyTestData.*;
import static com.vahundos.companies.web.CompanyAjaxController.AJAX_URL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyAjaxControllerTest extends AbstractTest {

    private MockMvc mockMvc;

    @Autowired
    private JsonUtil jsonUtil;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @PostConstruct
    private void postConstruct() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFindById() throws Exception {
        testGetForTo(COMPANY_WITH_CHILDREN_TO1);
    }

    @Test
    public void testFindAll() throws Exception {
        mockMvc.perform(get(AJAX_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonUtil.writeValue(new CompanyTo[]{COMPANY_TO1, COMPANY_TO2, COMPANY_TO3,
                        COMPANY_TO4, COMPANY_TO5, COMPANY_TO6, COMPANY_TO7})))
                .andDo(print());
    }

    @Test
    public void testFindAllWithChildren() throws Exception {
        mockMvc.perform(get(AJAX_URL + "/children"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonUtil.writeValue(new CompanyTo[]{COMPANY_WITH_CHILDREN_TO1,
                        COMPANY_WITH_CHILDREN_TO5})))
                .andDo(print());
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(AJAX_URL + "/" + COMPANY_TO1.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        CompanyTo forCreation = getForCreation();

        mockMvc.perform(post(AJAX_URL).contentType(MediaType.APPLICATION_JSON).content(jsonUtil.writeValue(forCreation)))
                .andExpect(status().isOk())
                .andDo(print());

        forCreation.setId(COMPANY_TO7.getId() + 1);

        testGetForTo(forCreation);
    }

    @Test
    public void testUpdate() throws Exception {
        CompanyTo forUpdate = getForUpdate();

        mockMvc.perform(put(AJAX_URL).contentType(MediaType.APPLICATION_JSON).content(jsonUtil.writeValue(forUpdate)))
                .andExpect(status().isOk())
                .andDo(print());

        testGetForTo(forUpdate);
    }

    private void testGetForTo(CompanyTo companyTo) throws Exception {
        mockMvc.perform(get(AJAX_URL + "/" + companyTo.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonUtil.writeIgnoreProps(companyTo,
                        "annualEarningsWithChildren", "parentId", "children")))
                .andDo(print());
    }
}