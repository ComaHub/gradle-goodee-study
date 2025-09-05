package dev.coma.study.common.security;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	private JwtTokenManager jwtTokenManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		
		this.jwtTokenManager = jwtTokenManager;
	}

	// Authorization: Bearer ${ACCESS_TOKEN} 형태로 요청받음
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		String authorization = request.getHeader("Authorization");
		System.out.println(authorization);
		
		if (authorization != null && authorization.startsWith("Bearer")) {
			String accessToken = authorization.substring(authorization.indexOf(" ") + 1);
			try {
				Authentication authentication = jwtTokenManager.verifyToken(accessToken);
				
				// 성공 시 Session에 로그인 정보 입력
				SecurityContextHolder.getContext().setAuthentication(authentication);
				log.info("Set authentication by token: {}", accessToken);
			} catch (Exception e) {
				log.warn("AccessToken is expired");
			}
		}
		
		chain.doFilter(request, response);
	}
}
