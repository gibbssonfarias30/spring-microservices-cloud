package com.backfcdev.organization.controller;

import com.backfcdev.organization.client.DepartmentClient;
import com.backfcdev.organization.client.EmployeeClient;
import com.backfcdev.organization.repository.OrganizationRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backfcdev.organization.model.Organization;

@RestController
public class OrganizationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

  @Autowired
  OrganizationRepository organizationRepository;
  @Autowired
  DepartmentClient departmentClient;
  @Autowired
  EmployeeClient employeeClient;

  @PostMapping("/")
  public Organization add(@RequestBody Organization organization) {
    LOGGER.info("Organization add: {}", organization);
    return organizationRepository.add(organization);
  }

  @GetMapping("/")
  public List<Organization> findAll(){
    LOGGER.info("Organization find");
    return organizationRepository.findAll();
  }

  @GetMapping("/{id}")
  public Organization findById(@PathVariable("id") Long id){
    LOGGER.info("Organization find: id={}", id);
    return organizationRepository.findById(id);
  }

  @GetMapping("/{id}/with-departments-and-employees")
  public Organization findByIdWithDepartmentsAndEmployees(@PathVariable("id") Long id) {
    LOGGER.info("Organization find: id={}", id);
    Organization organization = organizationRepository.findById(id);
    organization.setDepartments(departmentClient.findByOrganizationWithEmployees(organization.getId()));
    return organization;
  }

  @GetMapping("/{id}/with-employees")
  public Organization findByIdWithEmployees(@PathVariable("id") Long id) {
    LOGGER.info("Organization find: id={}", id);
    Organization organization = organizationRepository.findById(id);
    organization.setEmployees(employeeClient.findByOrganization(organization.getId()));
    return organization;
  }


}
