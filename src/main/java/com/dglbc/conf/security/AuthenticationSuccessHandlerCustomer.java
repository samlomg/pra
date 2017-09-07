package com.dglbc.conf.security;

import com.dglbc.login.entity.User;
import com.dglbc.login.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by gdlbc on 2016/12/25.
 */
@Component
@Slf4j
public class AuthenticationSuccessHandlerCustomer extends SimpleUrlAuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private UserRepository userRepository;

    public AuthenticationSuccessHandlerCustomer(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String targetUrl = determineTargetUrl(authentication);
        System.err.println("URL:"+targetUrl);
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
        UserDetailsCustom userDetailsCustom = (UserDetailsCustom) authentication.getPrincipal();
        System.err.println("UserId:"+userDetailsCustom.getId());
        User user=userRepository.findById(userDetailsCustom.getId());
        user.setLastLogin(new java.sql.Date(new Date().getTime()));
        user.setLastLoginIP(getIpAddress(request));
        userRepository.save(user);


        redirectStrategy.sendRedirect(request, response, targetUrl);
    }


    public String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }

        if (isDba(roles)) {
            url = "/db";
        } else if (isAdmin(roles)) {
            url = "/admin";
        } else if (isUser(roles)) {
            url = "/home";
        } else {
            url = "/welcome";
        }

        return url;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("access_index")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("access_all")) {
            return true;
        }
        return false;
    }

    private boolean isDba(List<String> roles) {
        if (roles.contains("ROLE_DBA")) {
            return true;
        }
        return false;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
