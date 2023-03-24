package com.mis.controller;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/adduser")
    public String adduser(@RequestBody  User user) throws CustException {
        userService.add(user);
        return "Added User Successfully";
    }
    @GetMapping("/login")
    public String login(@RequestBody User user) throws CustException {
        if(userService.valid(user))
            return "Welcome";
        else
            return "Invalid Id/Password";
    }
}
