package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Floor;
import com.mis.bookingrepositories.FloorRepo;
import com.mis.customclasses.Custom;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FloorService {


    private final FloorRepo floorRepo;
    private final BuildingService buildingService;

    private final FloorService floorService;

    public FloorService(FloorRepo floorRepo, BuildingService buildingService, FloorService floorService) {
        this.floorRepo = floorRepo;
        this.buildingService = buildingService;
        this.floorService = floorService;
    }

    public void addFloor(Floor floor) {
        floorRepo.save(floor);
    }

    public boolean verifyFloor(Custom custom) throws CustException {
        boolean opt = buildingService.verifyBuilding(custom.getBuildingName());

        if(opt == false){
            throw new CustException("No mentioned floor found in given building");
        }

        Optional<Floor> opt1 = floorRepo.findById(custom.getFloor().getFloorNo());

        if(opt1.isEmpty()){
            throw new CustException("No mentioned floor available");
        }

        return true;
    }

}
