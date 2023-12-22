package com.backfcdev.organization;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.backfcdev.organization.model.Organization;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
            "spring.cloud.discovery.enabled=false",
            "spring.cloud.config.discovery.enabled=false"

    }

)
public class OrganizationControllerTests {

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  void findAll(){
    Organization[] organizations = restTemplate.getForObject("/",
        Organization[].class);
    assertTrue(organizations.length > 0);
  }

  @Test
  void add(){
    Organization organization = new Organization("Test", "Test Street");
    organization = restTemplate.postForObject("/", organization, Organization.class);
    assertNotNull(organization);
    assertNotNull(organization.getId());
    assertEquals(3, organization.getId());
  }

  @Test
  void findById(){
    Organization organization = restTemplate.getForObject("/{id}", Organization.class, 1);
    assertNotNull(organization);
    assertNotNull(organization.getId());
    assertEquals(1, organization.getId());
  }


}
