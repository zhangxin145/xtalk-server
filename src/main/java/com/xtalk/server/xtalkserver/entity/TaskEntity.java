package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-02-27 11:14:32 
 */

@Entity
@Table ( name ="task" , schema = "")
public class TaskEntity extends BaseEntity<TaskEntity,Long>{


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 任务名称
	 */
   	@Column(name = "taskName" )
	private String taskName;

	/**
	 * 任务类型
	 */
   	@Column(name = "taskType" )
	private Long taskType;

	/**
	 * 任务彩种
	 */
   	@Column(name = "lotCode" )
	private Long lotCode;

	/**
	 * 定时任务规则
	 */
   	@Column(name = "cronTask" )
	private String cronTask;

	/**
	 * 任务开始时间
	 */
   	@Column(name = "startTime" )
	private Date startTime;

	/**
	 * 任务结束时间
	 */
   	@Column(name = "endTime" )
	private Date endTime;

	/**
	 * 说明
	 */
   	@Column(name = "description" )
	private String description;

	/**
	 * 内容
	 */
   	@Column(name = "content" )
	private String content;

	/**
	 * 创建时间
	 */
   	@Column(name = "ctime" )
	private Date ctime;

	/**
	 * 创建人
	 */
   	@Column(name = "cid" )
	private Long cid;

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

	public String getTaskName() {
		return this.taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Long getTaskType() {
		return this.taskType;
	}

	public void setTaskType(Long taskType) {
		this.taskType = taskType;
	}

	public Long getLotCode() {
		return this.lotCode;
	}

	public void setLotCode(Long lotCode) {
		this.lotCode = lotCode;
	}

	public String getCronTask() {
		return this.cronTask;
	}

	public void setCronTask(String cronTask) {
		this.cronTask = cronTask;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Long getCid() {
		return this.cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
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
					"taskName='" + taskName + '\'' +
					"taskType='" + taskType + '\'' +
					"lotCode='" + lotCode + '\'' +
					"cronTask='" + cronTask + '\'' +
					"startTime='" + startTime + '\'' +
					"endTime='" + endTime + '\'' +
					"description='" + description + '\'' +
					"content='" + content + '\'' +
					"ctime='" + ctime + '\'' +
					"cid='" + cid + '\'' +
					"utime='" + utime + '\'' +
					"uid='" + uid + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
