package com.example.student_managemt_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title="Student Management System OPEN API",
				version = "1.0.0",
				description="Student Management System OPEN API documentation"
		),
		servers = @Server(
				url ="http://aws-public-ip:8080/api/v1",
				description="Student Management System OPEN API url"
		)
)
public class StudentManagemtSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagemtSystemApplication.class, args);
	}

}
