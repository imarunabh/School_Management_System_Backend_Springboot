package com.scms.utils;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	public static final String SECRET="a62cc54bdaf76aade22d7516eb4cf3eae28525c4d6ab7c27b172cbb64dfb345b";
	
	public Boolean validateToken(String token,UserDetails userDetails) {
		final String userName = extractUsernName(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public String extractUsernName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T>T extractClaim(String token,Function<Claims,T> resolver){
		Claims claim = extractAllClaims(token);
		return resolver.apply(claim);
		
	}
	
	public Claims extractAllClaims(String token){
		return Jwts
			.parser()
			.verifyWith(getSignKey())
			.build()
			.parseSignedClaims(token)
			.getPayload();
		
	}
	
	public String generateToken(String userName){
		return Jwts
			.builder()
			.subject(userName)
			.issuedAt(new Date(System.currentTimeMillis()))
			.expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
			.signWith(getSignKey())
			.compact();
		
	}
	
	private SecretKey getSignKey() {
		byte[] keyBytes = Decoders.BASE64URL.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
