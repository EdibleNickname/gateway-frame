package com.can.response.enums;

/**
 * @description: 角色名字
 * @author: LCN
 * @date: 2018-05-21 10:45
 */
public enum RoleName {

	/** 游客 */
	VISITOR("ROLE_VISITOR", "游客"),
	/** 读者 */
	READER("ROLE_READER", "读者"),
	/** 作者 */
	WRITER("ROLE_WRITER", "作者"),
	/** 超级管理员 */
	ADMIN("ROLE_ADMIN", "超級管理员");

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
