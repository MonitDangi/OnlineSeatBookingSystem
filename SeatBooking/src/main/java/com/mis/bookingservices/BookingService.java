package com.mis.bookingservices;

import com.mis.bookingmodels.Building;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.BookingRepo;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private final BookingRepo bookingRepo;
    public BookingService(BookingRepo bookingRepo){
        this.bookingRepo = bookingRepo;
    }
    public boolean validateUser(Custom custom){
        String userId = custom.getUserId();
        String userPassword = custom.getUserPassword();
        Optional<User> validUser = bookingRepo.validateUser(userId, userPassword);
        return validUser.isPresent();
    }
    public ResponseEntity<String> findByLocation(Custom custom) {
        if(!validateUser(custom)){
            return new ResponseEntity<>("No such user exist.", HttpStatus.BAD_REQUEST);
        }
        List<Building> buildingList = bookingRepo.findAvailableSeatsAtLocation(custom.getBuildingLocation());
        StringBuilder buildings = new StringBuilder();
        for(Building b : buildingList) buildings.append(b.toString1()).append("\n");
        return new ResponseEntity<>(buildings.toString(),HttpStatus.FOUND);
    }
}
