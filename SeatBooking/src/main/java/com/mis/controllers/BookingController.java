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
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByLocation")
    public ResponseEntity<String> findByLocation(@RequestBody Location location){
        return bookingService.findByLocation(location);
    }
    @PostMapping("/bookseat")
    public ResponseEntity<String> bookseat(@RequestBody Custom custom) throws CustException, ParseException {
        System.out.println(custom.toString());
        bookingService.bookseat(custom);
        return new ResponseEntity<>("Seat Booked Successfully and a mail regarding the same has been sent to you registered mail id", HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/cancelSeat")
    public ResponseEntity<String> cancelSeat(@RequestBody Custom custom) throws CustException
    {
        bookingService.cancelseat(custom);
        return new ResponseEntity<>("Booking Cancelled Successfully",HttpStatus.ACCEPTED);
    }
    @GetMapping("/getAllSeatsForTime")
    public ResponseEntity<String> getAllSeatsForTime(@RequestBody Custom custom) throws CustException, ParseException {
        bookingService.validateDates(custom);
        bookingService.validateTime(custom);
        List<Seat> seatList = seatService.getAllRoom();
        List<Booking> clasBookings = bookingService.getClashingSeats();
        List<Integer>clashSeatIds = new ArrayList<>();
        for(Booking b: clasBookings){
            for(Seat s: seatList){
                if(s.getSeatId() == b.getSeatId()){
                    if(bookingService.isClash(b.getStartDate(), b.getStartTime().substring(0,5), b.getEndTime().substring(0,5), b.getEndDate(), custom)){
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
