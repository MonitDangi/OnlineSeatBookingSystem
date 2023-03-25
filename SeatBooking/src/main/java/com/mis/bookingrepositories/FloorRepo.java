package com.mis.bookingrepositories;

import com.mis.bookingmodels.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FloorRepo extends JpaRepository<Floor, Integer> {

    @Query(value = "select f from Floor f where f.building.buildingName=?1")
    List<Floor> findByBuilding(String buildingName);
    @Query(value = "select f from Floor f where f.building.buildingName = ?1")
    List<Floor> findAllFloors(String buildingName);
    @Query(value = "select f from Floor f where f.floorNo = ?1 and f.building.buildingName = ?2")
    Optional<Floor> findFloor(Integer floorNo, String buildingName);
}
