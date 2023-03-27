package com.mis.bookingservices;

import com.mis.bookingmodels.Floor;
import com.mis.bookingmodels.Seat;
import com.mis.bookingrepositories.FloorRepo;
import com.mis.bookingrepositories.SeatRepo;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {
    private final SeatRepo seatRepo;
    private final FloorRepo floorRepo;

    public SeatService(SeatRepo seatRepo, FloorRepo floorRepo) {
        this.seatRepo = seatRepo;
        this.floorRepo = floorRepo;
    }

    public void addSeat(Seat s){
        seatRepo.save(s);
    }

    public ResponseEntity<String> listAllSeats(Custom custom) {
        System.out.println(custom.getRoom().getRoomNo());
        System.out.println(custom.getFloor().getFloorNo());
        System.out.println(custom.getBuilding().getBuildingName());
        int fNo = custom.getFloor().getFloorNo();

        List<Seat> seats =  seatRepo.getAllSeats(fNo).get();
        if(seats.isEmpty()) System.out.println("Seat list is Empty");
        StringBuilder str = new StringBuilder();
        for(Seat s:seats){
            str.append(s.toString1()).append("\n");
        }
        return new ResponseEntity<>(str.toString(), HttpStatus.ACCEPTED);
    }
}
