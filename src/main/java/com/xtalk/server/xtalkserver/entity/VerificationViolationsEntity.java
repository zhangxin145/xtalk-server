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
@Table ( name ="verification_violations" , schema = "")
public class VerificationViolationsEntity {
	@Id // 添加一个空的id标识，因为jpa在映射实体是需要一个id，这个必须

   	@Column(name = "ip" )
	private String ip;

   	@Column(name = "time" )
	private Date time;

   	@Column(name = "count" )
	private Long count;

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public VerificationViolationsEntity() {
	}

	@Override
	public String toString() {
		return "{" +
					"ip='" + ip + '\'' +
					"time='" + time + '\'' +
					"count='" + count + '\'' +
				'}';
	}

}
