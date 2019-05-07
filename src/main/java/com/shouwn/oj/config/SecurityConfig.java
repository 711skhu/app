package com.shouwn.oj.config;

import com.shouwn.oj.model.entity.member.Student;
import com.shouwn.oj.security.JwtProperties;
import com.shouwn.oj.security.config.EnableJwtSecurity;
import com.shouwn.oj.security.config.JwtSecurityConfigurerAdapter;
import com.shouwn.oj.service.member.MemberAuthService;
import com.shouwn.oj.service.member.StudentService;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableJwtSecurity
public class SecurityConfig extends JwtSecurityConfigurerAdapter {

	private final StudentService studentService;

	private final StudentJwtProperties jwtProperties;

	public SecurityConfig(StudentService studentService, StudentJwtProperties jwtProperties) {
		this.studentService = studentService;
		this.jwtProperties = jwtProperties;
	}

	@Override
	public MemberAuthService<Student> memberServiceUsingSecurity() {
		return this.studentService;
	}

	@Override
	public JwtProperties jwtProperties() {
		return this.jwtProperties;
	}
}

