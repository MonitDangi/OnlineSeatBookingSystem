package com.mis.bookingservices;

import com.mis.bookingmodels.Room;
import com.mis.bookingmodels.Seat;
import com.mis.bookingrepositories.RoomRepo;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final SeatService seatService;
    public RoomService(RoomRepo roomRepo, SeatService seatService) {
        this.roomRepo = roomRepo;
        this.seatService = seatService;
    }

    public boolean checkRoom(Room room) {
        Optional<Room> exist = roomRepo.checkRoom(room.getRoomNo(), room.getFloor(), room.getBuildingName());
        return exist.isPresent();
    }
    public ResponseEntity<String> addRoom(Custom custom){
        Room room = custom.getRoom();
        if(checkRoom(room))return new ResponseEntity<>("Room Already Exist.", HttpStatus.ALREADY_REPORTED);
        roomRepo.save(room);
        int roomCapacity = room.getNumberOfSeats();
        for(int i = 0; i < roomCapacity; i++){
            Seat s = new Seat(i,room.getFloor().getFloorNo(), custom.getBuildingName(), room);
            seatService.addSeat(s);
        }
        String roomString = room.toString() + "\nAdded Successfully.";
        return new ResponseEntity<>(roomString, HttpStatus.ALREADY_REPORTED);
    }
}
