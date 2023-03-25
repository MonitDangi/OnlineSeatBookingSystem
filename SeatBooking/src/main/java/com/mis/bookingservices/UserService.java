package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.EmailService.EmailSenderService;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final EmailSenderService emailSenderService;
    public UserService(EmailSenderService emailSenderService, UserRepo userRepo, PasswordEncoder passwordEncoder){
        this.emailSenderService =emailSenderService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
    public void add(User user) throws CustException {

        Optional<User>us=userRepo.findById(user.getUserId());
        if(us.isPresent())
        {
            throw new CustException("User Already Exists");
        }
        emailSenderService.sendSimpleEmail(user.getUserEmail(), "Registration Successful","Thanks for registering");
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public boolean verifyUser(User user) throws CustException {
        Optional<User>temp=userRepo.findById(user.getUserId());
        if(temp.isEmpty())
        {
            throw new CustException("No such user found");
        }
        return passwordEncoder.matches(user.getPassword(),temp.get().getPassword());
    }
    
}