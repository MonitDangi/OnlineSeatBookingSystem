package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
@Autowired
UserRepo repo;
    public void adduser(User user) throws CustException {
        Optional<User>us=repo.findById(user.getUserId());
        if(us.isPresent())
        {
            throw new CustException("User Already Exists");
        }

    }
}
