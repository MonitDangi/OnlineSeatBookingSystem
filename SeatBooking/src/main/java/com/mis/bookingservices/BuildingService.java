package com.mis.bookingservices;

import com.mis.CustException.CustException;
import com.mis.bookingmodels.Building;
import com.mis.bookingrepositories.BuildingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuildingService {

    @Autowired
    private BuildingRepo buildingRepo;

    public void addBuilding(Building building) throws CustException {
        Optional<Building> opt = buildingRepo.findById(building.getBuildingName());
        if(!opt.isEmpty()){
            throw new CustException("Building Already Exists");
        }
        buildingRepo.save(building);
    }
}
