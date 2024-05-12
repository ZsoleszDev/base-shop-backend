package hu.thesis.baseshop.authorization.controller;

import hu.thesis.baseshop.authorization.dao.UserDao;
import hu.thesis.baseshop.authorization.entity.User;
import hu.thesis.baseshop.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser"})
    public User registerUser(@RequestBody User user){
            return userService.registerNewUser(user);
    }

}
