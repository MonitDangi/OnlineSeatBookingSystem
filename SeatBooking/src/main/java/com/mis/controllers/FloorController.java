package com.mis.controllers;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.User;
import com.mis.bookingservices.BuildingService;
import com.mis.bookingservices.FloorService;
import com.mis.bookingservices.UserService;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Floor")
public class FloorController {

    private final FloorService floorService;
    private final UserService userService;
    private final BuildingService buildingService;

    public FloorController(FloorService floorService, UserService userService, BuildingService buildingService) {
        this.floorService = floorService;
        this.userService = userService;
        this.buildingService = buildingService;

    }

    @PostMapping("/addFloor")
    public ResponseEntity<String> addFloor(@RequestBody Custom custom) throws CustException {
        //verify user.
        boolean valid = userService.verifyUser(custom.getUser());

        //verifyBuilding
        buildingService.verifyBuilding(custom.getBuildingName());

        //
        floorService.addFloor(custom.getFloor());

        return new ResponseEntity<>("Floor in corresponding Building Added successfully", HttpStatus.ACCEPTED);

    }
}
