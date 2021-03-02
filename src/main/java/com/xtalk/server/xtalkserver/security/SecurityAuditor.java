package com.xtalk.server.xtalkserver.security;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
/**
 * @author xin.z
 */
public class SecurityAuditor implements AuditorAware {

	@Override
	public Optional getCurrentAuditor() {
		Long uid = SecurityUtil.getCurrentUser();
		return Optional.ofNullable(uid);
	}
}
