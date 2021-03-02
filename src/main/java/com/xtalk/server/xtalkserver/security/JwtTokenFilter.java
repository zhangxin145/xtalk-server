package com.xtalk.server.xtalkserver.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xin.z
 */
@Component
public class JwtTokenFilter extends BasicAuthenticationFilter {

	public JwtTokenFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConst.header);
		if (header == null || !header.startsWith(SecurityConst.prefix)) {
			System.out.println("======");
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(SecurityConst.header);
		if (token != null) {
			Claims claims = Jwts.parser().setSigningKey(SecurityConst.secret)
					.parseClaimsJws(token.replace(SecurityConst.prefix, "")).getBody();

			String user = claims.getSubject();
			List<String> roles = (List<String>) claims.get("roles");
			ArrayList<GrantedAuthority> list = new ArrayList<>();
			if (roles != null) {
				for (String a : roles) {
					GrantedAuthority g = new SimpleGrantedAuthority(a);
					list.add(g);
				}
			}
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, list);
			}
			return null;
		}
		return null;
	}

}
