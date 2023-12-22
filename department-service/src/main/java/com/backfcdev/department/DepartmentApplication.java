package com.backfcdev.department;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.backfcdev.department.model.Department;
import com.backfcdev.department.repository.DepartmentRepository;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(info =
@Info(title = "Department API", version = "1.0", description = "Documentation Department API v1.0")
)
public class DepartmentApplication {

  public static void main(String[] args) {
    SpringApplication.run(DepartmentApplication.class, args);
  }

  @Bean
  DepartmentRepository repository() {
    DepartmentRepository repository = new DepartmentRepository();
    repository.add(new Department(1L, "Development"));
    repository.add(new Department(1L, "Operations"));
    repository.add(new Department(2L, "Development"));
    repository.add(new Department(2L, "Operations"));
    return repository;
  }

}
