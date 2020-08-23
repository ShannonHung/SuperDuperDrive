package com.finalproject.SuperDuperDrive.FinalProject;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrdererContext;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.h2.tools.Server;
import java.sql.SQLException;
import java.util.Optional;

@SpringBootApplication()
public class FinalProjectApplication{
	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

}
