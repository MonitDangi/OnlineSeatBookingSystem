package com.mis.controller;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Building;
import com.mis.bookingrepositories.BuildingRepo;
import com.mis.bookingservices.BookingService;
import com.mis.bookingservices.BuildingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BuildingServices")
public class BuildingController {

    private final BuildingService buildingService;
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

        @PostMapping("/addBuilding")
        public String addBuilding(@RequestBody Building building) throws CustException {
            buildingService.addBuilding(building);
            return "BRAVO.....YOU ADDED A NEW BUILDING.....";
    }
}
