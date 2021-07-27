package com.revature.daos;

import com.revature.entities.LoginForm;
import com.revature.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DAOService implements PinDAO, UserDAO {

    public User validateLogin(LoginForm loginForm) {
        logger.info("validating user...");
        User currentUser = getUserByEmail(loginForm.getUsername());
        if (currentUser != null) {
            if (currentUser.getPassword().equals(loginForm.getPassword())) {
                logger.info("User successfully logged in.");
                return currentUser;
            }
        }
        logger.info("User does not exist with this username and password.");
        return null;
    }

}
