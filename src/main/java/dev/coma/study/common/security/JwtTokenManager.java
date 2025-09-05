package dev.coma.study.common.security;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import dev.coma.study.member.MemberDTO;
import dev.coma.study.member.MemberRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenManager {
	// access token 유효시간
	@Value("${jwt.access-token-age}")
	private Long accessTokenAge;
	// refresh token 유효시간
	@Value("${jwt.refresh-token-age}")
	private Long refreshTokenAge;
	// 발급자
	@Value("${jwt.issuer}")
	private String issuer;
	// Custom Key
	@Value("${jwt.custom-key}")
	private String customKey;
	// Key
	private SecretKey secretKey;
	@Autowired
	private MemberRepository memberRepository;
	
	// 생성자
	@PostConstruct
	public void init() {
		String keyData = Base64.getEncoder().encodeToString(customKey.getBytes());
		secretKey = Keys.hmacShaKeyFor(keyData.getBytes());
	}
	
	// 토큰 생성 메서드
	private String createToken(Authentication authentication, Long tokenAge) {
		return Jwts.builder()
							 .subject(authentication.getName())
							 .claim("roles", authentication.getAuthorities())
							 .issuedAt(new Date(System.currentTimeMillis()))
							 .expiration(new Date(System.currentTimeMillis() + tokenAge))
							 .issuer(issuer)
							 .signWith(secretKey)
							 .compact()
							 ;
	}
	
	public String makeAccessToken(Authentication authentication) {
		return this.createToken(authentication, accessTokenAge);
	}
	
	public String makeRefreshToken(Authentication authentication) {
		return this.createToken(authentication, refreshTokenAge);
	}
	
	// 토큰 검증 메서드
	public Authentication verifyToken(String token) throws Exception {
		// 검증 실패 시 Exception 발생
		Claims claims = Jwts.parser()
												.verifyWith(secretKey)
												.build()
												.parseSignedClaims(token)
												.getPayload()
												;

		// 검증 성공 시 진행
		Optional<MemberDTO> result = memberRepository.findById(claims.getSubject());
		MemberDTO memberDTO = result.get();
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(memberDTO, null, memberDTO.getAuthorities());
		return authentication;
	}
}
