package com.dglbc.conf.security;

import com.dglbc.login.entity.User;
import com.dglbc.login.repository.RoleRepository;
import com.dglbc.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by gdlbc on 2016/12/25.
 */
@Component
public class UserDetailsServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user =userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Email "+email+" not found");
        }
        return new UserDetailsCustom(user,userRepository);
    }
}
