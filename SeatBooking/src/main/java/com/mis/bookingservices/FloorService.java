package com.mis.bookingservices;

import com.mis.bookingrepositories.FloorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloorService {

    @Autowired
    private FloorRepo floorRepo;
}
