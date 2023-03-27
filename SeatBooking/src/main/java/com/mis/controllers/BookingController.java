package com.mis.controllers;

import com.mis.CustException.CustException;
import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import com.mis.customclasses.Custom;
import com.mis.customclasses.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class BookingController {
    private final BookingService bookingService;
    private final BuildingService buildingService;
    public BookingController(BookingService bookingService, BuildingService buildingService){
        this.bookingService = bookingService;
        this.buildingService = buildingService;
    }
    public boolean validateDates(Custom custom)throws CustException{
        String dateFormat = "yyyy/MM/dd";
        SimpleDateFormat startingDate = new SimpleDateFormat(custom.getBooking().getStartDate());
        SimpleDateFormat endDate = new SimpleDateFormat(custom.getBooking().getEndDate());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        try{
            Date d1 = startingDate.parse(dateFormat);
            Date d2 = endDate.parse(dateFormat);
            LocalDate currentDate = LocalDate.now();
            String strCurrentDate = currentDate.format(dtf);
            SimpleDateFormat curr = new SimpleDateFormat(strCurrentDate);
            Date d3 = curr.parse(dateFormat);
            if(d1.compareTo(d2) > 1 || d1.compareTo(d3) < 0)throw new CustException("End date should be greater than start date start date should be greater then current date.");
        }
        catch (Exception e){
            throw new CustException("Invalid date or time format.");
        }
        return true;
    }
    @GetMapping("/findByLocation")
    public ResponseEntity<String> findByLocation(@RequestBody Location location){
        return bookingService.findByLocation(location);
    }
    @PostMapping("/bookseat")
    public ResponseEntity<String> bookseat(@RequestBody Custom custom) throws CustException{
        System.out.println(custom.toString());
        bookingService.bookseat(custom);
        return new ResponseEntity<>("Seat Booked Successfully and a mail regarding the same has been sent to you registered mail id", HttpStatus.ACCEPTED);
    }
//    @GetMapping("/getAllSeatsForTime")
//    public ResponseEntity<String> getAllSeatsForTime(Custom custom) throws CustException {
//    }
}
