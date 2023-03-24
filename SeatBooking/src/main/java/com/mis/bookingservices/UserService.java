package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User verifyUser(String userId, String userPassword) throws CustException {
        Optional<User> u = userRepo.findById(userId);
        if(u.isEmpty()){
            throw new CustException("Permission Denied.....You Are not an Admin.");
        }

        if(!passwordEncoder.matches(userPassword,u.get().getPassword())){
            throw new CustException("Permission Denied.....You Are not an Admin/wrong Password");
        }

        return u.get();
    }
}

