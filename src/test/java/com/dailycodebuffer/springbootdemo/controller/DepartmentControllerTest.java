package com.dailycodebuffer.springbootdemo.controller;

import com.dailycodebuffer.springbootdemo.entity.Department;
import com.dailycodebuffer.springbootdemo.service.DepartmetnService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmetnService departmetnService;

    private Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .departmentAddress("Dallas")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    @Test
    void shouldSaveDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .departmentAddress("Dallas")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();

        Mockito.when(departmetnService.saveDepartment(inputDepartment))
                .thenReturn(department);
    mockMvc
        .perform(
            post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    "{\n"
                        + "    \"departmentName\": \"IT\",\n"
                        + "    \"departmentAddress\": \"Dallas\",\n"
                        + "    \"departmentCode\": \"IT-06\"\n"
                        + "}"))
        .andExpect(status().isOk());
    }

    @Test
    void shouldFetchDepartmentById() throws Exception {
        Mockito.when(departmetnService.fetchDepartmentById(1L))
                .thenReturn(department);
        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}