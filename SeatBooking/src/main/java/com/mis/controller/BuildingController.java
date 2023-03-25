//package com.mis.controller;
//
//import com.mis.CustException.CustException;
//import com.mis.bookingmodels.Building;
//import com.mis.bookingmodels.User;
//import com.mis.bookingrepositories.BuildingRepo;
//import com.mis.bookingservices.BookingService;
//import com.mis.bookingservices.BuildingService;
//import com.mis.bookingservices.UserService;
//import com.mis.customclasses.Custom;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/BuildingServices")
//public class BuildingController {
//
//    private final BuildingService buildingService;
//    private final UserService userService;
//
//    public BuildingController(BuildingService buildingService, UserService userService) {
//        this.buildingService = buildingService;
//        this.userService = userService;
//    }
//
//
//
//        @PostMapping("/addBuilding")
//        public ResponseEntity<String> addBuilding(@RequestBody Custom custom) throws CustException {
//            // verifying user.
//            User u = userService.valid(custom.get);
//
//            // user verified
//            Building b = new Building();
//            b.setBuildingName(custom.getBuildingName());
//            b.setLocation(custom.getLocation());
//
//            buildingService.addBuilding(b);
//            return new ResponseEntity<>("BRAVO.....YOU ADDED A NEW BUILDING...", HttpStatus.ACCEPTED);
//    }
//}