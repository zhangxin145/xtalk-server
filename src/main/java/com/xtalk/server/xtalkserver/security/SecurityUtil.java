package com.xtalk.server.xtalkserver.security;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * @author xin.z
 */
public class SecurityUtil {


	public static String getUserToken(long userId) {
		String user = String.valueOf(userId);
		return jwkToken(user, user, SecurityConst.user, SecurityConst.expiration);
	}

	public static String getBackendToken(long adminId) {
		String backend = String.valueOf(adminId);
		return jwkToken(backend, backend, SecurityConst.backend, SecurityConst.expiration);
	}

	/**
	 * 生成Token
	 * @param id 用户ID
	 * @param subject 主体内容(userName)
	 * @param audience 接收方 (角色信息-admin/user/anonymous)
	 * @param ttlMillis Token有效日期
	 * @return String
	 */
	protected static String jwkToken(String id, String subject, String audience, long ttlMillis) {
		// 加密方式
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConst.secret);
		Key key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		long nowMillis = System.currentTimeMillis();
		// 签发时间
		Date now = new Date(nowMillis);

		/**
		 * jwt生成token id: 用户id subject：主体内容 issuedAt：签发时间 Audience：接收方 signWith：加密方式
		 */
		JwtBuilder builder = Jwts.builder().setId(id).setSubject(subject).setIssuedAt(now).setAudience(audience)
				.signWith(signatureAlgorithm, key);

		if (ttlMillis >= 0) {
			// 过期时间
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}
		return builder.compact();
	}

	public static Long getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		if (principal == null) {
			return null;
		}

		Long uid = 0L;
		User user = null;
		String username = null;

		if (principal instanceof User) {
			user = (User) principal;
			username = user.getUsername();
		}
		else {
			username = principal.toString();
		}

		switch (username) {
			case SecurityUser.ANONYMOUS:
				uid = 1L;
				break;
			case SecurityUser.USER:
				uid = 2L;
				break;
			case SecurityUser.ADMIN:
				uid = 3L;
				break;
			case SecurityUser.SYSTEM:
				uid = 4L;
				break;
			default:
				uid = NumberUtils.toLong(username, 0L);
		}

		return uid;
	}
}
