package com.forrequia.oauth2.service;

import com.forrequia.oauth2.dao.UserDAO;
import com.forrequia.oauth2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        Optional<User> user = userDAO.findByUsername(input);

        if (!user.isPresent())
            throw new BadCredentialsException("Bad credentials");

        new AccountStatusUserDetailsChecker().check(user.get());

        return user.get();
    }

    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
