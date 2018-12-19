package com.forrequia.oauth2.configuration;

import com.forrequia.oauth2.dao.UserDAO;
import com.forrequia.oauth2.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserDAO userDAO;

    @Autowired
    public CustomAuthenticationProvider(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();


        Optional<User> user = userDAO.findByUsername(name).filter(u -> u.getPassword().equals(DigestUtils.sha512Hex(password)));

        return user.map(u -> new UsernamePasswordAuthenticationToken(
                name, password, u.getAuthorities())).orElse(null);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
