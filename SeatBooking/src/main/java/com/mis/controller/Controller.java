package com.mis.controller;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingservices.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class Controller {
    @Autowired
    Service service;
    @PostMapping("/adduser")
    public String adduser(@RequestBody User user) throws CustException{
        service.adduser(user);
        return "Added User Successfully";
    }
}
