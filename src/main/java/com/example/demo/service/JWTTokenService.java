package com.example.demo.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTTokenService {
	
	private static String SECRET_KEY = "404E65266556A586E327257538782F413FF4428472B4B6250645367566B5970";
	
	public JWTTokenService() throws NoSuchAlgorithmException {
		//generate secret key
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
		SecretKey s = keyGen.generateKey();
		SECRET_KEY = Base64.getEncoder().encodeToString(s.getEncoded());
	}

	public String generateToken(String username) {
		Map<String, Object> map = new HashMap<>();
		// TODO Auto-generated method stub
		return Jwts.builder()
				.claims()
				.add(map)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))//60 milli seconds 60 minutes 30 minutes, this token valid for 30 minutes
				.and()
				.signWith(getKey())
				.compact(); //compact() method will send token for as
	}

//	private Key getKey() {
//		// TODO Auto-generated method stub
//		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // base64 convert our string to bytes
//		return Keys.hmacShaKeyFor(keyBytes); //hmacShaKeyFor is key generating algorithm
//	}
	
	private SecretKey getKey() {
		// TODO Auto-generated method stub
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); // base64 convert our string to bytes
		return Keys.hmacShaKeyFor(keyBytes); //hmacShaKeyFor is key generating algorithm
	}

	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		//we need a claims bcz claims have all the data
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		// TODO Auto-generated method stub
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		//verifyWith accept only secretkey not a key
		return Jwts.parser()
				.verifyWith(getKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		// TODO Auto-generated method stub
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		// TODO Auto-generated method stub
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		return extractClaim(token, Claims::getExpiration);
	}
	
	

}
