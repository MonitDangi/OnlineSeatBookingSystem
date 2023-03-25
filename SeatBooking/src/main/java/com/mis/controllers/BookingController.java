package com.mis.controllers;

import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BookingService")
public class BookingController {
    private final BookingService bookingService;
    private final BuildingService buildingService;
    public BookingController(BookingService bookingService, BuildingService buildingService){
        this.bookingService = bookingService;
        this.buildingService = buildingService;
    }
    @RequestMapping("/findByLocation")
    public ResponseEntity<String> findByLocation(Custom custom){
        return bookingService.findByLocation(custom);
    }
    @RequestMapping("/findByBuilding")
    public ResponseEntity<String> findByBuilding(Custom custom){
        if(!bookingService.validateUser(custom)){
            return new ResponseEntity<>("No such user exist.", HttpStatus.BAD_REQUEST);
        }
        return buildingService.findByBuilding(custom);
    }
}
