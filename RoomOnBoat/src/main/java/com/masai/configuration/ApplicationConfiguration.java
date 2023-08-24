package com.masai.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;
@Configuration
public class ApplicationConfiguration {
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.cors(cors ->{
					cors.configurationSource(new CorsConfigurationSource() {
						@Override
						public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
							CorsConfiguration configuration= new CorsConfiguration();
							configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
							configuration.setAllowedMethods(Collections.singletonList("*"));
							configuration.setAllowCredentials(true);
							configuration.setAllowedHeaders(Collections.singletonList("*"));
							configuration.setExposedHeaders(Arrays.asList("Authorization"));
							return configuration;
						}
					});
				})
				.authorizeHttpRequests(auth ->{
					auth
//					.requestMatchers("/add/users","/add/hosts","/login/host","/login/user","/login/admin","/logout").permitAll()
							.requestMatchers("/host/**").hasRole("HOST")
							.requestMatchers("/admin/**").hasRole("ADMIN")
							.requestMatchers("/user/**").hasRole("USER")
							.requestMatchers("/swagger-ui*/**","/v3/api-docs/**","/logIn","/add/admin","/add/users","/add/hosts","/user/get_properties","/user/property/{propertiId}/get_rooms").permitAll();
				})
				.csrf(csrf -> csrf.disable())
				.addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)
				.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
				.formLogin(Customizer.withDefaults())
				.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}