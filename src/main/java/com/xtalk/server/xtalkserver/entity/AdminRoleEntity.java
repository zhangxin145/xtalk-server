package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-21 20:22:07 
 */

@Entity
@Table ( name ="admin_role" , schema = "")
public class AdminRoleEntity {


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 用户ID
	 */
   	@Column(name = "admin_id" )
	private Long adminId;

	/**
	 * 角色ID
	 */
   	@Column(name = "role_id" )
	private Long roleId;

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
   	@Column(name = "etime" )
	private Date etime;

	/**
	 * 修改人
	 */
   	@Column(name = "eid" )
	private Long eid;

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

	public Long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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

	public Date getEtime() {
		return this.etime;
	}

	public void setEtime(Date etime) {
		this.etime = etime;
	}

	public Long getEid() {
		return this.eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
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
					"adminId='" + adminId + '\'' +
					"roleId='" + roleId + '\'' +
					"ctime='" + ctime + '\'' +
					"cid='" + cid + '\'' +
					"etime='" + etime + '\'' +
					"eid='" + eid + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
