package com.backfcdev.employee;


import com.backfcdev.employee.model.Employee;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.cloud.discovery.enabled=false",
                "spring.cloud.config.discovery.enabled=false"

        })
public class EmployeeAppTests {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void findAll() {
    Employee[] employees = restTemplate.getForObject("/", Employee[].class);
    Assertions.assertTrue(employees.length > 0);
  }

  @Test
  void findById() {
    Employee employee = restTemplate.getForObject("/{id}", Employee.class, 1L);
    Assertions.assertNotNull(employee);
    Assertions.assertNotNull(employee.getId());
    Assertions.assertNotNull(employee.getName());
    Assertions.assertEquals(1L, employee.getId());
  }

  @Test
  void findByOrganization() {
    Employee[] employees = restTemplate.getForObject("/organization/{organizationId}", Employee[].class, 1L);
    Assertions.assertTrue(employees.length > 0);
  }

  @Test
  void findByDepartment() {
    Employee[] employees = restTemplate.getForObject("/department/{departmentId}", Employee[].class, 1L);
    Assertions.assertTrue(employees.length > 0);
  }

  @Test
  void add() {
    Employee employee = Instancio.create(Employee.class);
    employee = restTemplate.postForObject("/", employee, Employee.class);
    Assertions.assertNotNull(employee);
    Assertions.assertNotNull(employee.getId());
    Assertions.assertNotNull(employee.getName());
  }
}
