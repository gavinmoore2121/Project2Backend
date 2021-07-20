package com.revature.web;

import com.revature.daos.PinDAO;
import com.revature.daos.UserDAO;
import com.revature.entities.LoginForm;
import com.revature.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//TODO this class will map all necessary functions to Spring services and servlets.

@Controller
public class MappingService implements PinDAO, UserDAO {

    /**
     * Validate a user's login, and return the user with the given email or a not-found message.
     * @param loginForm: A JSON object containing the fields username and password, containing the user-input
     *                 email and password attempt respectively.
     * @return The user's full account as a JSON object, or a not-found response.
     */
    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    public static ResponseEntity<User> validateLogin(@RequestBody LoginForm loginForm) {
        User currentUser = UserDAO.getUserByEmail(loginForm.getUsername());
        if (currentUser.getPassword().equals(loginForm.getPassword())) {
            return ResponseEntity.ok(currentUser);
        }
        else return ResponseEntity.notFound().build();
    }

}
