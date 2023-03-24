package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepo userRepo;
    public void add(User user) throws CustException {

        Optional<User>us=userRepo.findById(user.getUserId());
        if(us.isPresent())
        {
            throw new CustException("User Already Exists");
        }
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean valid(User user) throws CustException {
        Optional<User>us=userRepo.findById(user.getUserId());
        if(us.isEmpty()){
            throw new CustException("No such user found");
        }
        return passwordEncoder.matches(user.getPassword(), us.get().getPassword());
    }
}
