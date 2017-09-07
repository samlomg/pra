package com.dglbc.conf.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by gdlbc on 2016/12/25.
 */
public class DaoAuthenticationProviderCustomer extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        return super.authenticate(authentication);
    }
}
