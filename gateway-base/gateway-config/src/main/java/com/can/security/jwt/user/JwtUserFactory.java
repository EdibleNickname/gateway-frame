package com.can.security.jwt.user;

import com.can.entity.User;
import com.can.entity.UserRole;
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
                user.isEnabled(),
                user.getLastPasswordResetDate(),
				mapToGrantedAuthorities(user.getUserRoles())
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<UserRole> userRoleSet) {
        return userRoleSet.stream()
                .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().getRoleName()))
                .collect(Collectors.toList());
    }
}
