package com.revature.daos;

import com.revature.entities.LoginForm;
import com.revature.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class DAOService implements PinDAO, UserDAO {

    public User validateLogin(LoginForm loginForm) {
        User currentUser = getUserByEmail(loginForm.getUsername());
        if (currentUser.getPassword().equals(loginForm.getPassword())) {
            return currentUser;
        }
        else return null;
    }
}
