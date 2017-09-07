package com.dglbc.conf.security;

import com.dglbc.login.entity.Role;
import com.dglbc.login.entity.User;
import com.dglbc.login.entity.UserRole;
import com.dglbc.login.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

/**
 * Created by gdlbc on 2016/12/25.
 */
public class UserDetailsCustom extends User implements UserDetails {

    private UserRepository userRepository;

    public UserDetailsCustom(String name, String email, String password, Date lastLogin, String lastLoginIP, Date lastUpdate, Date createTime, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, String remark, UserRole userRole) {
        super(name, email, password, lastLogin, lastLoginIP, lastUpdate, createTime, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, remark, userRole);
    }

    public UserDetailsCustom(User user,UserRepository userRepository) {
        super();
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setPassword(user.getPassword());
            this.setLastLogin(user.getLastLogin());
            this.setLastLoginIP(user.getLastLoginIP());
            this.setLastUpdate(user.getLastUpdate());
            this.setCreateTime(user.getCreateTime());
            this.setUserRole(user.getUserRole());
            this.setEmail(user.getEmail());

            this.setAccountNonExpired(user.isAccountNonExpired());
            this.setAccountNonLocked(user.isAccountNonLocked());
            this.setCredentialsNonExpired(user.isCredentialsNonExpired());
            this.setEnabled(user.isEnabled());
            this.userRepository=userRepository;

        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        User user=this.userRepository.findByEmail(this.getEmail());
        Set<Role> userRoless=user.getUserRole().getRoles();
        if (userRoless != null) {
            for (Role role : userRoless) {
//                System.err.println("Role:"+role.toString());
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getName();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return super.isAccountNonExpired();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return super.isAccountNonLocked();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.security.core.userdetails.UserDetails#
     * isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return super.isCredentialsNonExpired();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.security.core.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return super.isEnabled();
    }
}
