package com.example.crudsample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@OpenAPIDefinition(info = @Info(title ="Crud API",version ="1.0",description ="Simple SpringBoot App"), security = {@SecurityRequirement(name ="Authorization")} )
@SecurityScheme(name = "javainuseapi", scheme = "Authorization", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class CrudSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudSampleApplication.class, args);
    }
}

