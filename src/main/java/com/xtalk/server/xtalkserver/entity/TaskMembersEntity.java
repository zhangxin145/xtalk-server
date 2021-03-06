package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-02-27 11:14:32 
 */

@Entity
@Table ( name ="task_members" , schema = "")
public class TaskMembersEntity {


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 任务id
	 */
   	@Column(name = "taskId" )
	private Long taskId;

	/**
	 * 成员id
	 */
   	@Column(name = "memberId" )
	private Long memberId;

	/**
	 * 说明
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 创建人
	 */
   	@Column(name = "cid" )
	private Long cid;

	/**
	 * 创建时间
	 */
   	@Column(name = "ctime" )
	private Date ctime;

	/**
	 * 修改时间
	 */
   	@Column(name = "utime" )
	private Date utime;

	/**
	 * 修改人
	 */
   	@Column(name = "uid" )
	private Long uid;

	/**
	 * 逻辑删除:0=未删除,1=已删除
	 */
   	@Column(name = "deleted" )
	private Integer deleted;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getMemberId() {
		return this.memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return this.utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"taskId='" + taskId + '\'' +
					"memberId='" + memberId + '\'' +
					"description='" + description + '\'' +
					"cid='" + cid + '\'' +
					"ctime='" + ctime + '\'' +
					"utime='" + utime + '\'' +
					"uid='" + uid + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
