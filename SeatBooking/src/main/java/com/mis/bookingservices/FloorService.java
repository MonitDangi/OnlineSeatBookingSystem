package com.mis.bookingservices;

import com.mis.bookingmodels.Floor;
import com.mis.bookingrepositories.FloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService {

    @Autowired
    private FloorRepo floorRepo;

    public void addFloor(Floor floor) {
        floorRepo.save(floor);
    }
}
