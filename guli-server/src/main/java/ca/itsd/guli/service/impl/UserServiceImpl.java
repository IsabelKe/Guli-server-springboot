package ca.itsd.guli.service.impl;


import ca.itsd.guli.domain.Login;
import ca.itsd.guli.domain.User;
import ca.itsd.guli.dto.UserDTO;
import ca.itsd.guli.repository.UserRepository;
import ca.itsd.guli.service.UserService;
import ca.itsd.guli.util.ResultEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *this class handles all business logic for all user related requests
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * add a new user
     * @param User the new user
     */
    public void insertUser(User User) {
        userRepository.save(User);
    }

    /**
     * find a user by the id
     * @param id of the user
     * @return an user object or null it no user matches
     */
    public User findUser(int id) {
        return userRepository.getOne(id);
    }


    /**
     * handle user login
     * @param username of the user
     * @param password of the user
     * @return a customized Login object or null it no user matches
     */
    public ResultEntity<Login> login(String username, String password) {
        //1.find the user from the database with this username
        User userDB = userRepository.findUserByLogAccount(username);
        if(userDB!=null) {
            //2. compare the password of UserDB with the input password
            if (userDB.getPassword().equals(password)) {
                //customize the return data
                List<String> roleNames = new ArrayList<>();
                for (int i = 0; i < userDB.getRoles().size(); i++) {
                    roleNames.add(userDB.getRoles().get(i).getRoleName());
                }
                Login login = new Login(userDB.getUserName(), roleNames);
                //only return the user name and a list of role names to the client side
                return ResultEntity.successWithData(login);
            } else {
                return ResultEntity.failed("The password is wrong");
            }
        }
        return ResultEntity.failed("User name is wrong");
    }


    /**
     * find all users
     * @return a customized  userDTO list or null if no user matches
     */
    public ResultEntity<List<UserDTO>> findAll() {
        List<User> users=userRepository.findAll();
        //check if there are any users
        if(users.size()>0) {
            //map the users to userDTO
            ModelMapper modelMapper = new ModelMapper();
            List<UserDTO> userDTOS = modelMapper.map(users, new TypeToken<List<UserDTO>>() {
            }.getType());
            generateRoles(userDTOS,users);
            return ResultEntity.successWithData(userDTOS);
        }
        return ResultEntity.successWithoutData("No users!");
    }

    /**
     * generate a list of role names for an user
     * @param userDTOS
     * @param users
     */
    private void generateRoles(List<UserDTO> userDTOS,List<User> users)
    {
        //customize the return data
        for (int i = 0; i < users.size(); i++) {
            //get the user
            User user=users.get(i);
            List<String> roleNames = new ArrayList<>();
            for (int j = 0; j < user.getRoles().size(); j++) {
                roleNames.add(user.getRoles().get(j).getRoleName());
            }
            userDTOS.get(i).setRoleNames(roleNames);
        }

    }
}
