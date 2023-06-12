package com.example.learningtesting.employee;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMVC;

    @Test
    @Disabled
    void getAllEmployees() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/api/employee");
        MvcResult mvcResult = mockMVC.perform(request).andReturn();

//        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("[]");
    }

    @Test
    @Disabled
    void getEmployeeByEmail() {
    }

    @Test
    @Disabled
    void addNewEmployee() {
    }

    @Test
    @Disabled
    void deleteEmployeeById() {
    }
}