package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Floor;
import com.mis.bookingmodels.Room;
import com.mis.bookingmodels.Seat;
import com.mis.bookingrepositories.RoomRepo;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepo roomRepo;
    private final SeatService seatService;
    private final FloorService floorService;
    public RoomService(RoomRepo roomRepo, SeatService seatService,  FloorService floorService) {
        this.roomRepo = roomRepo;
        this.seatService = seatService;
        this.floorService = floorService;
    }

    public boolean checkRoom(Room room, int floorNo, String buildingName) {
        Optional<Room> exist = roomRepo.checkRoom(room.getRoomNo(), floorNo, buildingName);
        return exist.isPresent();
    }
    public ResponseEntity<String> addRoom(Custom custom){
        Room room = custom.getRoom();
        System.out.println(room.getRoomNo()+"  "+custom.getFloor().getFloorNo()+"  "+custom.getBuilding().getBuildingName());
        if(checkRoom(room, custom.getFloor().getFloorNo(),custom.getBuilding().getBuildingName()))return new ResponseEntity<>("Room Already Exist.", HttpStatus.ALREADY_REPORTED);
        Floor f = floorService.getFloor(custom);
        Room r = new Room(custom.getRoom().getRoomNo(), custom.getBuildingName(), custom.getRoom().getNumberOfSeats(), f);
        System.out.println(r.toString1());
        roomRepo.save(r);
        floorService.updateCapacity(custom.getBuildingName(), f.getFloorNo(), r.getNumberOfSeats());
        Room r1 = roomRepo.getRoom(custom.getBuildingName(), f.getFloorNo(), room.getRoomNo()).get();
        int roomCapacity = room.getNumberOfSeats();
        for(int i = 0; i < roomCapacity; i++){
            Seat s = new Seat(i+1,f.getFloorNo(), custom.getBuildingName(), r1);
            seatService.addSeat(s);
        }
        String roomString = room.toString1() + "\nAdded Successfully.";
        return new ResponseEntity<>(roomString, HttpStatus.ACCEPTED);
    }

    public List<Room> getAllRooms(String buildingName, int floorNo) {
        return roomRepo.getAllRooms(buildingName, floorNo);
    }
    public boolean validateRoom(Custom custom)throws CustException {
        if(!floorService.verifyFloor(custom)){
            throw new CustException("No such floor exist.");
        }
        System.out.println("Floor Validated.");
        if(roomRepo.validateRoom(custom.getRoom().getRoomNo(), custom.getFloor().getFloorNo(), custom.getBuilding().getBuildingName()).isEmpty()){
            throw new CustException("No such room exist");
        }
        return true;
    }
}
