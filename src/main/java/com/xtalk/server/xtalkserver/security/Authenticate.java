package com.xtalk.server.xtalkserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author xin.z
 */
public class Authenticate {

	@Autowired
	@Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

	public String token(String username, String password) {
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password);
		final org.springframework.security.core.Authentication authentication = authenticationManager
				.authenticate(auth);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final User user = (User) authentication.getPrincipal();
		final Claims claims = Jwts.claims().setSubject(user.getUsername());
		final List<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		claims.put("roles", roles);

		String token = SecurityConst.prefix + Jwts.builder().setClaims(claims)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConst.expiration))
				.signWith(SignatureAlgorithm.HS512, SecurityConst.secret).compact();

		return token;
	}

}
