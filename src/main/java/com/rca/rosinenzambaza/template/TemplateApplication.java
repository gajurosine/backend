package com.rca.rosinenzambaza.template;

import com.rca.rosinenzambaza.template.v1.enums.EBasicRoles;
import com.rca.rosinenzambaza.template.v1.services.RoleService;
import org.hibernate.validator.internal.IgnoreForbiddenApisErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
@EnableAsync
@IgnoreForbiddenApisErrors(reason = "json")
@EnableWebSecurity
@EnableGlobalAuthentication()
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TemplateApplication {

	private final RoleService roleService;

	@Autowired
	public TemplateApplication (RoleService roleService) {
		this.roleService = roleService;
	}

	public static void main(String[] args) {
		org.springframework.boot.SpringApplication.run(TemplateApplication.class, args);
	}

	@Bean
	public void insertRolesFromEnum(){
		Set<EBasicRoles> basicRoles = new HashSet<>();
		basicRoles.add(EBasicRoles.ADMIN);
		basicRoles.add(EBasicRoles.USER);


		for (EBasicRoles role : basicRoles){
			roleService.createRole(role.name());
		}
	}


}
