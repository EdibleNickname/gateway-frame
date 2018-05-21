package com.can.response.enums;

/**
 * @description: 角色名字
 * @author: LCN
 * @date: 2018-05-21 10:45
 */
public enum RoleName {

	VISITOR("ROLE_VISITOR", "游客"),
	READER("ROLE_READER", "读者"),
	WRITER("ROLE_WRITER", "作者"),
	ADMIN("ROLE_ADMIN", "超級管理者");

	/** 角色名 */
	private String roleName;

	/** 角色名中文 */
	private String roleNameZh;

	private RoleName(String roleName, String roleNameZh) {
		this.roleName = roleName;
		this.roleNameZh = roleNameZh;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNameZh() {
		return roleNameZh;
	}

	public void setRoleNameZh(String roleNameZh) {
		this.roleNameZh = roleNameZh;
	}

}
