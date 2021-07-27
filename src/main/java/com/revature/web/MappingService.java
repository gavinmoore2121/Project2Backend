package com.revature.web;

import com.revature.daos.DAOService;
import com.revature.entities.LoginForm;
import com.revature.entities.Pin;
import com.revature.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * A Controller class utilizing the Spring MVC API to query the user database and return appropriate results.
 */
@Controller
public class MappingService {

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
     *                 email and password attempt respectively. Appropriately formatted JSON is '{"username":
     *                 "userInputUsername", "password": "userInputPassword"}'.
     * @return The user's full account as a JSON object, or a null reference.
     */
    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> validateLogin(@RequestBody LoginForm loginForm, @Autowired DAOService daoService) {
        User currentUser = daoService.validateLogin(loginForm);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }


    /**
     * Get a list all pins a user owns.
     * @param user: The user in JSON, with the properties "email", "displayName", "password" and "userPins".
     * @return A list of all user pins in JSON format, with the properties "name", "desc", "lat", and "long".
     */
    @RequestMapping(value= "/getUserPins", method= RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Pin>> getUserPins(@RequestBody User user, @Autowired DAOService daoService) {
        user = daoService.getUserByEmail(user.getEmail());
        return new ResponseEntity<List<Pin>>(user.getUserPins(), HttpStatus.OK);
    }

    /**
     * Update a user in the database with new information. The user in the database with the
     * matching email will be changed.
     * @param user: The user in JSON, with the properties "email", "displayName", "password" and "userPins".
     * @return The updated user.
     */
    @RequestMapping(value = "/updateUser", method= RequestMethod.PUT, produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> updateUser(@RequestBody User user, @Autowired DAOService daoService) {
        daoService.updateUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Create a user and add it the database.
     * @param user: The user in JSON, with the properties "email", "displayName", "password" and "userPins".
     * @return The created user.
     */
    @PostMapping(value = "/createUser", produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<User> createUser(@RequestBody User user, @Autowired DAOService daoService) {
        daoService.saveUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Create a pin and add it to the database.
     * @param pin: The pin in JSON format, with the properties "name", "desc", "lat", and "long".
     * @return The newly created pin.
     */
    @PostMapping(value = "/createPin", produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  ResponseEntity<Pin> createPin(@RequestBody Pin pin, @Autowired DAOService daoService) {
        daoService.savePin(pin);
        return new ResponseEntity<>(pin, HttpStatus.OK);
    }

    /**
     * Delete a pin from the database.
     * @param pin: The pin in JSON format, with the properties "name", "desc", "lat", and "long".
     * @return A response entity with an empty body.
     */
    @RequestMapping(value = "/deletePin", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity deletePin(@RequestBody Pin pin, @Autowired DAOService daoService) {
        daoService.deletePin(pin);
        return ResponseEntity.noContent().build();
    }


}
