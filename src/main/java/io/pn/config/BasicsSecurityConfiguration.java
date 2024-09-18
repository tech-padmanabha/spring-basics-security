package io.pn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class BasicsSecurityConfiguration {
	
	@Value("${white.list.url}")
	private String white_list_url;
	
	@Value("${auth.list.url}")
	private String auth_list_url;
	
	@Autowired
	private AuthenticationProvider authProvider;

	@Bean
	SecurityFilterChain configSecurity(HttpSecurity http) throws Exception {
		http
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(req ->
			req
			.requestMatchers(white_list_url).permitAll()
			.requestMatchers(auth_list_url).authenticated()
			.anyRequest().authenticated()
		);
		http.authenticationProvider(authProvider);
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return http.build();
	}
}
