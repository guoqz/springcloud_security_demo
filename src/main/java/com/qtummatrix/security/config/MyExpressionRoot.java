package com.qtummatrix.security.config;

import com.qtummatrix.security.entity.LoginUser;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("my")
public class MyExpressionRoot implements SecurityExpressionOperations {
    @Override
    public Authentication getAuthentication() {
        return null;
    }

    @Override
    public boolean hasAuthority(String s) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();

        // 判断用户权限集合中是否存在对应的权限
        return permissions.contains(s);
    }

    @Override
    public boolean hasAnyAuthority(String... strings) {
        return false;
    }

    @Override
    public boolean hasRole(String s) {
        return false;
    }

    @Override
    public boolean hasAnyRole(String... strings) {
        return false;
    }

    @Override
    public boolean permitAll() {
        return false;
    }

    @Override
    public boolean denyAll() {
        return false;
    }

    @Override
    public boolean isAnonymous() {
        return false;
    }

    @Override
    public boolean isAuthenticated() {
        return false;
    }

    @Override
    public boolean isRememberMe() {
        return false;
    }

    @Override
    public boolean isFullyAuthenticated() {
        return false;
    }

    @Override
    public boolean hasPermission(Object o, Object o1) {
        return false;
    }

    @Override
    public boolean hasPermission(Object o, String s, Object o1) {
        return false;
    }
}
