package com.can.security.jwt.user;

import com.can.entity.Role;
import com.can.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @description: JwtUser的生成工厂
 *
 * @author: LCN
 * @date: 2018-05-21 09:09
 */
public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getUserName(),
                user.getPassword(),
                user.getEnabled(),
                user.getLastPasswordResetDate(),
				mapToGrantedAuthorities(user.getRoleSet())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> userRoleSet) {

        return userRoleSet.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleName()))
                .collect(Collectors.toList());
    }
}
