package com.dailycodebuffer.springbootdemo.service;

import com.dailycodebuffer.springbootdemo.entity.Department;
import com.dailycodebuffer.springbootdemo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmetnServiceTest {

  @Autowired private DepartmetnService departmetnService;

  @MockBean private DepartmentRepository departmentRepository;

  @BeforeEach
  void setUp() {
    Department department =
        Department.builder()
            .departmentName("IT")
            .departmentAddress("Dallas")
            .departmentCode("IT-06")
            .departmentId(1L)
            .build();
    Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
  }

  @Test
  public void shouldFetchDepartmentByName() {

    String departmentname = "IT";
    Department found = departmetnService.fetchDepartmentByName(departmentname);
    assertEquals(departmentname, found.getDepartmentName());
  }
}