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
@Table ( name ="flyway_schema_history" , schema = "")
public class FlywaySchemaHistoryEntity {

	@Id // 添加一个空的id标识，因为jpa在映射实体是需要一个id，这个必须


   	@Column(name = "installed_rank" )
	private Long installedRank;

   	@Column(name = "version" )
	private String version;

   	@Column(name = "description" )
	private String description;

   	@Column(name = "type" )
	private String type;

   	@Column(name = "script" )
	private String script;

   	@Column(name = "checksum" )
	private Long checksum;

   	@Column(name = "installed_by" )
	private String installedBy;

   	@Column(name = "installed_on" )
	private Date installedOn;

   	@Column(name = "execution_time" )
	private Long executionTime;

   	@Column(name = "success" )
	private Integer success;

	public FlywaySchemaHistoryEntity() {
	}

	public Long getInstalledRank() {
		return this.installedRank;
	}

	public void setInstalledRank(Long installedRank) {
		this.installedRank = installedRank;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getScript() {
		return this.script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Long getChecksum() {
		return this.checksum;
	}

	public void setChecksum(Long checksum) {
		this.checksum = checksum;
	}

	public String getInstalledBy() {
		return this.installedBy;
	}

	public void setInstalledBy(String installedBy) {
		this.installedBy = installedBy;
	}

	public Date getInstalledOn() {
		return this.installedOn;
	}

	public void setInstalledOn(Date installedOn) {
		this.installedOn = installedOn;
	}

	public Long getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(Long executionTime) {
		this.executionTime = executionTime;
	}

	public Integer getSuccess() {
		return this.success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "{" +
					"installedRank='" + installedRank + '\'' +
					"version='" + version + '\'' +
					"description='" + description + '\'' +
					"type='" + type + '\'' +
					"script='" + script + '\'' +
					"checksum='" + checksum + '\'' +
					"installedBy='" + installedBy + '\'' +
					"installedOn='" + installedOn + '\'' +
					"executionTime='" + executionTime + '\'' +
					"success='" + success + '\'' +
				'}';
	}

}
