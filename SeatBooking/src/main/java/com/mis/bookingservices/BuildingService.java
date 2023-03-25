package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Building;
import com.mis.bookingmodels.Floor;
import com.mis.bookingrepositories.BuildingRepo;
import com.mis.bookingrepositories.FloorRepo;
import com.mis.customclasses.Custom;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

    private final BuildingRepo buildingRepo;
    private final FloorRepo floorRepo;

    public BuildingService(BuildingRepo buildingRepo, FloorRepo floorRepo){
        this.buildingRepo =buildingRepo;
        this.floorRepo =floorRepo;
    }
    public boolean validateBuilding(String buildingName){
        return buildingRepo.validateBuilding(buildingName).isPresent();
    }

    public void addBuilding(Building building) throws CustException {
        Optional<Building> opt = buildingRepo.findById(building.getBuildingName());
        if(opt.isPresent()){
            throw new CustException("Building Already Exists");
        }
        buildingRepo.save(building);
    }

    public ResponseEntity<String> findByBuilding(Custom custom) {
        String buildingName = custom.getBuildingName();
        if(!validateBuilding(buildingName)){
            return new ResponseEntity<>("No such building Exist.", HttpStatus.BAD_REQUEST);
        }
        List<Floor> floorsList = floorRepo.findByBuilding(buildingName);
        StringBuilder floors = new StringBuilder();
        for(Floor f : floorsList) floors.append(f.toString1()).append("\n");
        return new ResponseEntity<>(floors.toString(),HttpStatus.FOUND);
    }
}
