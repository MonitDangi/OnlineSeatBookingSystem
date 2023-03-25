package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Building;
import com.mis.bookingmodels.User;
import com.mis.bookingrepositories.BookingRepo;
import com.mis.customclasses.Custom;
import com.mis.customclasses.Location;
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
    public ResponseEntity<String> findByLocation(Location custom) {
        Optional<List<Building>> buildingLists = bookingRepo.findAvailableSeatsAtLocation(custom.getBuilding_location());
        StringBuilder buildings = new StringBuilder();
        List<Building>buildingList = buildingLists.get();
        if(buildingList.isEmpty())return new ResponseEntity<>("No Buildings Available at given Location", HttpStatus.NOT_FOUND);
        for(Building b : buildingList){
            buildings.append(b.toString1()).append("\n");
            System.out.println(b);
        }
        return new ResponseEntity<>(buildings.toString(),HttpStatus.FOUND);
    }
}
