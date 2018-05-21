package com.can.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-21 10:16
 */

@Data
@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "user_id", nullable = false )
	private User user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name = "role_id", nullable = false )
	private Role role;

}
