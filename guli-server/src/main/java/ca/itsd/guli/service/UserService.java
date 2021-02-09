package ca.itsd.guli.service;



import ca.itsd.guli.domain.Login;
import ca.itsd.guli.domain.User;
import ca.itsd.guli.dto.UserDTO;
import ca.itsd.guli.util.ResultEntity;

import java.util.List;


/**
 * this interface defines all the user related methods
 */
public interface UserService {

    /**
     * add a new user
     * @param user the new user
     */
     void insertUser(User user);

    /**
     * find a user by the id
     * @param id of the user
     * @return an user object or null it no user matches
     */
     User findUser(int id);

    /**
     * handle user login
     * @param username of the user
     * @param password of the user
     * @return a customized Login object or null if no user matches
     */
    ResultEntity<Login> login(String username, String password);

    /**
     * find all users
     * @return a customized  userDTO list or null if no user matches
     */
    ResultEntity<List<UserDTO>> findAll();
}
