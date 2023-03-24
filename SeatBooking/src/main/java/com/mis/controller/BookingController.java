package com.mis.controller;

import com.mis.bookingservices.BookingService;
import com.mis.customclasses.Custom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BookingService")
public class BookingController {
    private final BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    @RequestMapping("/findByLocation")
    public ResponseEntity<String> findByLocation(Custom custom){
        String userId = custom.getUserId();
        String userPassword = custom.getUserPassword();
        String location = custom.getLocation();
        return bookingService.findByLocation(userId, userPassword, location);
    }
}
