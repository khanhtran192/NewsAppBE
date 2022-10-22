package com.news.config;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {
	private final String JWT_SECRET = "khaidz";

	private final long EX_PIRATION = 604800000L;

	public String createToken(UserDetails userDetails) {

		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + EX_PIRATION))
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();

	}

	public String getUserNameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parsePlaintextJws(token);
			return true;
		} catch (MalformedJwtException e) {

		} catch (ExpiredJwtException ex) {
//            log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
//            log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
//            log.error("JWT claims string is empty.");
		}
		return false;
	}
}
