package com.can.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-20 21:57
 */

@Data
@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Column(name = "user_name", length = 50, unique = true, nullable = false)
	private String userName;

	@Column(name = "password", length = 100, nullable = false)
	private String password;

	@Column(name = "enabled", columnDefinition = "TINYINT(1) default 1")
	private boolean enabled;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_password_reset_date")
	private Date lastPasswordResetDate;

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRole> userRoles;

}
