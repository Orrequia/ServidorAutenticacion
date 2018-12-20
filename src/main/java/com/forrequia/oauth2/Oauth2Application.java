package com.forrequia.oauth2;

import com.forrequia.oauth2.dao.UserDAO;
import com.forrequia.oauth2.model.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Oauth2Application {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserDAO userDAO) {
        return args -> {

            User orrequia = new User();
            User valeria = new User();

            orrequia.setUsername("orrequia");
            orrequia.setEnabled(true);
            orrequia.setPassword(DigestUtils.sha512Hex("1234"));
            userDAO.save(orrequia);

            valeria.setUsername("valeria");
            valeria.setEnabled(true);
            valeria.setPassword(DigestUtils.sha512Hex("4321"));
            userDAO.save(valeria);
        };
    }
}

