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
 * @Date 2021-01-19 23:18:06 
 */

@Entity
@Table ( name ="login_logs" , schema = "")
public class LoginLogsEntity {


	@Id
	@GeneratedValue
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "userId" )
	private Long userId;

   	@Column(name = "ipAddress" )
	private Long ipAddress;

   	@Column(name = "os" )
	private String os;

   	@Column(name = "osVersion" )
	private String osVersion;

   	@Column(name = "carrier" )
	private String carrier;

   	@Column(name = "device" )
	private String device;

   	@Column(name = "manufacturer" )
	private String manufacturer;

   	@Column(name = "userAgent" )
	private String userAgent;

   	@Column(name = "createdAt" )
	private Date createdAt;

	public LoginLogsEntity() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getIpAddress() {
		return this.ipAddress;
	}

	public void setIpAddress(Long ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOs() {
		return this.os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getOsVersion() {
		return this.osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getCarrier() {
		return this.carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"userId='" + userId + '\'' +
					"ipAddress='" + ipAddress + '\'' +
					"os='" + os + '\'' +
					"osVersion='" + osVersion + '\'' +
					"carrier='" + carrier + '\'' +
					"device='" + device + '\'' +
					"manufacturer='" + manufacturer + '\'' +
					"userAgent='" + userAgent + '\'' +
					"createdAt='" + createdAt + '\'' +
				'}';
	}

}
