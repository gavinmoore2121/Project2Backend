package com.revature.web;

import com.revature.daos.PinDAO;
import com.revature.daos.UserDAO;
import com.revature.entities.LoginForm;
import com.revature.entities.Pin;
import com.revature.entities.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.util.List;


/**
 * A Controller class utilizing the Spring MVC API to query the user database and return appropriate results.
 */
@Controller
public class MappingService implements PinDAO, UserDAO {

    /**
     * A test method that returns a simple response to a get-request.
     * @param id The number to print, placed in the url.
     */
    @RequestMapping(value= "/testConnection", method= RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> basicGetMethod(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        return new ResponseEntity<>("Connection valid, here's a number: " + id + "!", HttpStatus.OK);
    }

    /**
     * Validate a user's login, and return the user with the given email or a not-found message.
     * @param loginForm: A JSON object containing the fields username and password, containing the user-input
     *                 email and password attempt respectively. Appropriately formatted JSON is '"username":
     *                 "userInputUsername", "password": "userInputPassword"'.
     * @return The user's full account as a JSON object, or a not-found response.
     */
    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> validateLogin(@RequestBody LoginForm loginForm) {
        User currentUser = UserDAO.getUserByEmail(loginForm.getUsername());
        if (currentUser.getPassword().equals(loginForm.getPassword())) {
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @RequestMapping(value= "/getUserPins", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Pin>> getUserPins(@RequestBody User user) {
        return new ResponseEntity<List<Pin>>(user.getUserPins(), HttpStatus.OK);
    }


    @RequestMapping(value = "/updateUser", method= RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        UserDAO.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping(value = "/createUser", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> createUser(@RequestBody User user) {
        System.out.println(user);
        //UserDAO.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/createPin", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  ResponseEntity<Pin> createPin(@RequestBody Pin pin) {
        System.out.println(pin);
        //PinDAO.savePin(pin);
        return new ResponseEntity<>(pin, HttpStatus.OK);
    }


    // This one might trigger foreign key constaints and fail, will be tested later.
    @RequestMapping(value = "/deletePin", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deletePin(@RequestBody Pin pin) {
        PinDAO.deletePin(pin);
        return ResponseEntity.noContent().build();
    }


}
