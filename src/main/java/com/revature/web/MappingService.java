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

import java.util.List;

//TODO this class will map all necessary functions to Spring services and servlets.

@Controller
public class MappingService implements PinDAO, UserDAO {

    @RequestMapping(value= "/testConnection", method= RequestMethod.GET, produces= MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public ResponseEntity<String> checkPage(@RequestParam(value = "id", defaultValue = "1") Integer id) {
        return new ResponseEntity<>("Connection valid, here's a number: " + id + "!", HttpStatus.OK);
    }

    /**
     * Validate a user's login, and return the user with the given email or a not-found message.
     * @param loginForm: A JSON object containing the fields username and password, containing the user-input
     *                 email and password attempt respectively.
     * @return The user's full account as a JSON object, or a not-found response.
     */
    @RequestMapping(value = "/validateLogin", method = RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> validateLogin(@RequestBody LoginForm loginForm) {
        User currentUser = UserDAO.getUserByEmail(loginForm.getUsername());
        if (currentUser.getPassword().equals(loginForm.getPassword())) {
            return new ResponseEntity<>(currentUser, HttpStatus.OK);
        }
        else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value= "/getUserPins", method= RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<Pin>> getUserPins(@RequestBody User user) {
        return new ResponseEntity<List<Pin>>(user.getUserPins(), HttpStatus.OK);
    }


    @RequestMapping(value = "/updateUser", method= RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateUser(@RequestBody User user) {
        UserDAO.updateUser(user);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User user) {
        UserDAO.saveUser(user);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/createPin", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity createPin(@RequestBody Pin pin) {
        PinDAO.savePin(pin);
        return ResponseEntity.noContent().build();
    }


    // This one might trigger foreign key constaints and fail, will be tested later.
    @RequestMapping(value = "/deletePin", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deletePin(@RequestBody Pin pin) {
        PinDAO.deletePin(pin);
        return ResponseEntity.noContent().build();
    }


}
