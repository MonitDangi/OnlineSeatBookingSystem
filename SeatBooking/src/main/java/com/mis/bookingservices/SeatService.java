package com.mis.bookingservices;

import com.mis.bookingmodels.Seat;
import com.mis.bookingrepositories.SeatRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    private final SeatRepo seatRepo;

    public SeatService(SeatRepo seatRepo) {
        this.seatRepo = seatRepo;
    }

    public void addSeat(Seat s){
        seatRepo.save(s);
    }
}
