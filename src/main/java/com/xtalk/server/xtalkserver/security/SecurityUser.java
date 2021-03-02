package com.xtalk.server.xtalkserver.security;

/**
 * @author xin.z
 */
public interface SecurityUser {

	public static final String ANONYMOUS = "anonymous";

	public static final String USER = "user";

	public static final String BACKEND = "backend";

	public static final String ADMIN = "admin";

	public static final String SYSTEM = "system";

	public static final String[] USERS = { ANONYMOUS, USER, BACKEND, ADMIN, SYSTEM };
}