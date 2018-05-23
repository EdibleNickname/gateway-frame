package com.can.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-23 17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotBlank(message="姓名不能为空")
	private String name;

	@NotBlank(message="密码不能为空")
	private String password;

}
