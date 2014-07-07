package org.simple.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.simple.user.LoginUser;

/**
 * 〈一句话功能简述〉<br> 工具类
 * 〈功能详细描述〉获取当前应用登录用户
 */
public class SecurityUtil {
    public static LoginUser getLoginUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context != null && context.getAuthentication() != null) {
            return (LoginUser) context.getAuthentication().getPrincipal();
        }
        return null;
    }
}
