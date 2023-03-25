package com.mis.controllers;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Room;
import com.mis.bookingservices.*;
import com.mis.customclasses.Custom;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomController {
    private final UserService userService;
    private final BuildingService buildingService;
    private final FloorService floorService;
    private final RoomService roomService;
    public RoomController(UserService userService, BuildingService buildingService, FloorService floorService, RoomService roomService){
        this.userService = userService;
        this.buildingService = buildingService;
        this.floorService = floorService;
        this.roomService = roomService;
    }

    @PostMapping("/addNewRoom")
    public ResponseEntity<String> addNewRoom(Custom custom) throws CustException {
        userService.verifyUser(custom.getUser());
        buildingService.verifyBuilding(custom.getBuildingName());
        floorService.verifyFloor(custom);
        Room r = new Room(custom.getRoom().getRoomNo(), custom.getBuildingName(), custom.getRoom().getNumberOfSeats(), custom.getFloor());
        return roomService.addRoom(r);
    }
}
