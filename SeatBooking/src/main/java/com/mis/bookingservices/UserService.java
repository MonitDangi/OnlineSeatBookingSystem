package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.EmailService.EmailSenderService;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.UserRepo;
import com.mis.customclasses.PassWord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
    protected String tokengenerate() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public boolean verifyUser(User user) throws CustException {
        Optional<User>temp=userRepo.findById(user.getUserId());
        if(temp.isEmpty())
        {
            throw new CustException("No such user found");
        }
        return passwordEncoder.matches(user.getPassword(),temp.get().getPassword());
    }
protected String token;
    public void forgotPassword(User user) throws CustException {
        Optional<User>temp=userRepo.findById(user.getUserId());
        if(temp.isEmpty())
        {
            throw new CustException("Invalid ID/Email");
        }
        if(!user.getUserEmail().equals(temp.get().getUserEmail()))
        {
            throw new CustException("User Id and Email does not match");
        }
       token=tokengenerate();
       emailSenderService.sendSimpleEmail(user.getUserEmail(), "Reset password request","This is your otp to reset your password "+token);
       token=passwordEncoder.encode(token);
    }

    public void resetPwd(PassWord passWord) throws CustException {
        if(passwordEncoder.matches(passWord.getToken(),token ))
        {
           passWord.setPassword(this.passwordEncoder.encode(passWord.getPassword()));
            userRepo.updatepassword(passWord.getPassword(),passWord.getUserid());
            token="";
        }
        else
        {
            throw new CustException("Invalid OTP");
        }
    }
}