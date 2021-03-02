package com.xtalk.server.xtalkserver.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description  
 * @Author  zx
 * @Date 2021-01-21 20:20:44 
 */

@Entity
@Table ( name ="role_permission" , schema = "")
public class RolePermissionEntity {


	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   	@Column(name = "id" )
	private Long id;

	/**
	 * 角色ID
	 */
   	@Column(name = "role_id" )
	private Long roleId;

	/**
	 * 权限ID
	 */
   	@Column(name = "permission_id" )
	private Long permissionId;

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

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
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
					"roleId='" + roleId + '\'' +
					"permissionId='" + permissionId + '\'' +
					"ctime='" + ctime + '\'' +
					"cid='" + cid + '\'' +
					"utime='" + utime + '\'' +
					"uid='" + uid + '\'' +
					"deleted='" + deleted + '\'' +
				'}';
	}

}
