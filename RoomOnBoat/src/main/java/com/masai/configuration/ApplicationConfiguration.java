package com.masai.configuration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
				.requestMatchers(HttpMethod.POST,"/add/users","/add/hosts","/host/logIn","/user/logIn","/admin/logIn","/logout").permitAll()
				.requestMatchers(HttpMethod.GET,"/host/{hostId}/property/{propertyId}/rooms","/host/{hostId}/properties").hasRole("HOST")
				.requestMatchers(HttpMethod.POST,"/host/{hostId}/properties","/host/{hostId}/property/{propertyId}/rooms","/host/{hostId}/update_email","/host/{hostId}/update_password","/host/{hostId}/update_status/booking/{bookingId}","/host/{hostId}/update_name").hasRole("HOST")
				.requestMatchers(HttpMethod.DELETE,"/host/{hostId}/delete_account","/host/{hostId}/property/{propertyId}/delete_room/{roomId}","/host/{hostId}/delete_property/{propertyId}").hasRole("HOST")
				.requestMatchers(HttpMethod.GET,"/admin/get_hosts","/admin/get_users","/admin/get_admins","/admin/get_properties","/admin/get_rooms").hasRole("ADMIN")
				.requestMatchers(HttpMethod.POST,"/admin/add_admin/host/{hostId}","/admin/add_admin/user/{userId}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/admin/delete_host/{hostId}","/admin/delete_user/{userId}","/admin/delete_room/{roomId}","/admin/delete_property/{propertyId}").hasRole("ADMIN")
				.requestMatchers(HttpMethod.GET,"/user/get_properties","/user/property/{propertyId}/get_rooms","/user/{userId}/get_your_booking").hasRole("USER")
				.requestMatchers(HttpMethod.POST,"/user/{userId}/update_email","/user/{userId}/update_password","/user/{userId}/update_name","/user/{userId}/book_room/{roomId}","/user/{userId}/confirm_booking/{bookingId}").hasRole("USER")
				.requestMatchers(HttpMethod.DELETE,"/user/{userId}/booking/{bookingId}/delete_booked_room/{roomId}","/user/{userId}/delete_account").hasRole("USER")
				.anyRequest().authenticated();
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
