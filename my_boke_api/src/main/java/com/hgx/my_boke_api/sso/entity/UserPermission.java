package com.hgx.my_boke_api.sso.entity;

import java.io.Serializable;

public class UserPermission implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String permissionId;
	private String permissionName;
	private String permissionUrl;
	private String permissionDescription;
	private String permissionFrom;
	public UserPermission() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPermission(String permissionId, String permissionName,
			String permissionUrl, String permissionDescription,
			String permissionFrom) {
		super();
		this.permissionId = permissionId;
		this.permissionName = permissionName;
		this.permissionUrl = permissionUrl;
		this.permissionDescription = permissionDescription;
		this.permissionFrom = permissionFrom;
	}
	public String getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	public String getPermissionName() {
		return permissionName;
	}
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}
	public String getPermissionUrl() {
		return permissionUrl;
	}
	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}
	public String getPermissionDescription() {
		return permissionDescription;
	}
	public void setPermissionDescription(String permissionDescription) {
		this.permissionDescription = permissionDescription;
	}
	public String getPermissionFrom() {
		return permissionFrom;
	}
	public void setPermissionFrom(String permissionFrom) {
		this.permissionFrom = permissionFrom;
	}
	
	
}
