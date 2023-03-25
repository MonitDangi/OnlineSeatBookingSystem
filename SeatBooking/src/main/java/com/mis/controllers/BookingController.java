package com.mis.controllers;

import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import com.mis.customclasses.Location;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private final BookingService bookingService;
    private final BuildingService buildingService;
    public BookingController(BookingService bookingService, BuildingService buildingService){
        this.bookingService = bookingService;
        this.buildingService = buildingService;
    }
    @GetMapping("/findByLocation")
    public ResponseEntity<String> findByLocation(@RequestBody Location location){
        return bookingService.findByLocation(location);
    }
}
