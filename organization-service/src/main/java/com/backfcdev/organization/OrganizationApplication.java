package com.backfcdev.organization;

import com.backfcdev.organization.repository.OrganizationRepository;
import com.backfcdev.organization.model.Organization;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info=
@Info(
    title="Organization API",
    version = "1.0",
    description = "Documentation Organization API v1.0")
)
public class OrganizationApplication {

  public static void main(String[] args) {
     SpringApplication.run(OrganizationApplication.class, args);
  }

  @Bean
  OrganizationRepository repository(){
    OrganizationRepository repository = new OrganizationRepository();
    repository.add(new Organization("Microsoft", "Redmond, Washington, USA"));
    repository.add(new Organization("Oracle", "Redwood City, California, USA"));
    return repository;
  }
}
