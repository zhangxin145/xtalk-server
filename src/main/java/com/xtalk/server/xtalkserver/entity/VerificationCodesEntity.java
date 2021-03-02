package com.xtalk.server.xtalkserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-19 23:18:07 
 */

@Entity
@Table ( name ="verification_codes" , schema = "")
public class VerificationCodesEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "region" )
	private String region;

   	@Column(name = "phone" )
	private String phone;

   	@Column(name = "sessionId" )
	private String sessionId;

   	@Column(name = "token" )
	private String token;

   	@Column(name = "createdAt" )
	private Date createdAt;

   	@Column(name = "updatedAt" )
	private Date updatedAt;

	public VerificationCodesEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"region='" + region + '\'' +
					"phone='" + phone + '\'' +
					"sessionId='" + sessionId + '\'' +
					"token='" + token + '\'' +
					"createdAt='" + createdAt + '\'' +
					"updatedAt='" + updatedAt + '\'' +
				'}';
	}

}
