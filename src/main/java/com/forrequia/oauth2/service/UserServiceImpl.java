package com.forrequia.oauth2.service;

import com.forrequia.oauth2.dao.UserDAO;
import com.forrequia.oauth2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service(value = "userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {

        Optional<User> user = userDAO.findByUsername(input);

        if (user == null)
            throw new BadCredentialsException("Bad credentials");

        new AccountStatusUserDetailsChecker().check(user.get());

        return user.get();
    }

    @Override
    public List<User> findAll(Pageable p) {
        return userDAO.findAll(PageRequest.of(p.getPageNumber(), p.getPageSize())).stream().collect(Collectors.toList());
    }

    public Optional<User> findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
