package com.mis.controllers;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Building;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.BuildingRepo;
import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import com.mis.bookingservices.UserService;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/BuildingServices")
public class BuildingController {

    private final BuildingService buildingService;
    private final UserService userService;

    public BuildingController(BuildingService buildingService, UserService userService) {
        this.buildingService = buildingService;
        this.userService = userService;
    }

    @PostMapping("/addBuilding")
    public ResponseEntity<String> addBuilding(@RequestBody Custom custom) throws CustException {
        //verifyUser
        userService.verifyUser(custom.getUser());

        //user verified, now adding building

        buildingService.addBuilding(custom.getBuilding());

        return new ResponseEntity<>("Bravo...you added a new building",HttpStatus.ACCEPTED);

    }
}
