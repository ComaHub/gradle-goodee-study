package dev.coma.study.common.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import dev.coma.study.common.security.CustomLogoutSuccessHandler;
import dev.coma.study.common.security.JwtAuthenticationFilter;
import dev.coma.study.common.security.JwtLoginFilter;
import dev.coma.study.common.security.JwtTokenManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;
	@Autowired
	private JwtTokenManager jwtTokenManager;
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security
		
		// CORS
		.cors((option) -> option.configurationSource(this.corsConfiguration()))
		
		// CSRF
		.csrf((option) -> option.disable())
		
		// Authorization
		.authorizeHttpRequests((option) -> { option
			.requestMatchers("/api/notice/add").hasRole("ADMIN")
			.requestMatchers("/api/notice").authenticated()
			.anyRequest().permitAll()
			;
		})
		
		// Form Login
		.formLogin((option) -> option.disable())
		
		// Logout
		.logout((option) -> { option
			.logoutUrl("/api/member/logout")
			.invalidateHttpSession(true)
			.deleteCookies("accessToken", "refreshToken")
			.logoutSuccessHandler(customLogoutSuccessHandler)
			;
		})
		
		// Session
		.sessionManagement((option) -> option.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		
		// HTTP Basic
		.httpBasic((option) -> option.disable())
		
		// Token Filter
		.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
		.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager))
		
		;
		
		return security.build();
	}
	
	CorsConfigurationSource corsConfiguration() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOriginPatterns(List.of("http://localhost:*"));
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "PUT", "DELETE"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setExposedHeaders(List.of("accessToken", "refreshToken"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
}
