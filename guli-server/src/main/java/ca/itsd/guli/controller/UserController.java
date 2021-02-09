package ca.itsd.guli.controller;



import ca.itsd.guli.domain.Login;
import ca.itsd.guli.dto.UserDTO;
import ca.itsd.guli.service.UserService;
import ca.itsd.guli.domain.User;
import ca.itsd.guli.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController to handle all user related requests
 */
@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService adminService;


    /**
     * handle user login request
     * @param login that includes the user name and password
     * @return an user object if the login is successful. Otherwise null.
     */
    @PostMapping(value = "/login")
    public ResultEntity<Login> login(@RequestBody Login login) {

        return adminService.login(login.getUsername(),login.getPassword());
    }

    /**
     * find a list of user
     * @return a list of users
     */
    @RequestMapping(value = "")
    public  ResultEntity<List<UserDTO>> getUsers() {
       return adminService.findAll();
    }
}
