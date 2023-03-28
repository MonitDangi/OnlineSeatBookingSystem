package com.mis.controllers;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Booking;
import com.mis.bookingmodels.Room;
import com.mis.bookingmodels.Seat;
import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import com.mis.bookingservices.SeatService;
import com.mis.customclasses.Custom;
import com.mis.customclasses.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.midi.Soundbank;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class BookingController {
    private final BookingService bookingService;
    private final BuildingService buildingService;
    private final SeatService seatService;
    public BookingController(BookingService bookingService, BuildingService buildingService, SeatService seatService){
        this.bookingService = bookingService;
        this.buildingService = buildingService;
        this.seatService = seatService;
    }
    public void validateTime(Custom custom)throws CustException{
        String startTime =  custom.getBooking().getStartTime();
        String endTime = custom.getBooking().getEndTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime.parse(startTime, formatter);
            LocalTime.parse(endTime, formatter);
        }catch (Exception e){
            throw new CustException("Invalid time");
        }
    }
    public void validateDates(Custom custom)throws CustException{
        SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        String startingDate = custom.getBooking().getStartDate();
        String endDate = custom.getBooking().getEndDate();
        try{
            Date d1 = dtf.parse(startingDate);
            Date d2 = dtf.parse(endDate);
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String today = currentDate.format(formatter);
            Date d3 = dtf.parse(today);
            if(d1.compareTo(d2) > 1 || d1.compareTo(d3) < 0)throw new CustException("End date should be greater than start date start date should be greater then current date.");
        }
        catch (Exception e){
            throw new CustException("Invalid date or time format.");
        }
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

    @GetMapping("/getAllSeatsForTime")
    public ResponseEntity<String> getAllSeatsForTime(@RequestBody Custom custom) throws CustException, ParseException {
        validateDates(custom);
        validateTime(custom);
        List<Seat> seatList = seatService.getAllRoom();
        List<Booking> clasBookings = bookingService.getClashingSeats();
        System.out.println(seatList.size());
        System.out.println(clasBookings.size());
        List<Integer>clashSeatIds = new ArrayList<>();
        for(Booking b: clasBookings){
            for(Seat s: seatList){
                if(s.getSeatId() == b.getSeatId()){
                    if(bookingService.isClash(b.getStartDate(), b.getStartTime().substring(0,5), b.getEndTime().substring(0,5), b.getEndDate(), custom)){
                        System.out.println(s.getSeatId());
                        clashSeatIds.add(s.getSeatId());
                    }
                }
            }
        }
        StringBuilder availableSeats = new StringBuilder();
        for(Seat s: seatList){
            if(!clashSeatIds.contains(s.getSeatId()))availableSeats.append(s.toString1()+"\n");
        }
        return new ResponseEntity<>(availableSeats.toString(), HttpStatus.FOUND);
    }
}
