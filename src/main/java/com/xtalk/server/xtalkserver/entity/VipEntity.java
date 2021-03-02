package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-28 18:59:26 
 */

@Entity
@Table ( name ="vip" , schema = "")
public class VipEntity {


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 名称
	 */
   	@Column(name = "name" )
	private String name;

   	@Column(name = "sort" )
	private Long sort;

	/**
	 * 权限 _隔开
	 */
   	@Column(name = "permission" )
	private String permission;

	/**
	 * 介绍
	 */
   	@Column(name = "intro" )
	private String intro;

	/**
	 * 状态 0-正常 1-删除
	 */
   	@Column(name = "status" )
	private Integer status;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSort() {
		return this.sort;
	}

	public void setSort(Long sort) {
		this.sort = sort;
	}

	public String getPermission() {
		return this.permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
					"name='" + name + '\'' +
					"sort='" + sort + '\'' +
					"permission='" + permission + '\'' +
					"intro='" + intro + '\'' +
					"status='" + status + '\'' +
					"ctime='" + ctime + '\'' +
					"cid='" + cid + '\'' +
					"utime='" + utime + '\'' +
					"uid='" + uid + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
