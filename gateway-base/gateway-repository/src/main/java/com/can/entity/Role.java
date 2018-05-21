package com.can.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-21 09:41
 */

@Data
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(name = "role_name", length = 50)
	private String roleName;

	@Column(name = "role_name_zh", length = 50)
	private String roleNameZh;

	@OneToMany(mappedBy="role", cascade=CascadeType.ALL)
	private Set<UserRole> userRoles;

}
