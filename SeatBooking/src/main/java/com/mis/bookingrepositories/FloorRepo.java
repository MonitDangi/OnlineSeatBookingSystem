package com.mis.bookingrepositories;

import com.mis.bookingmodels.Floor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FloorRepo extends JpaRepository<Floor, Integer> {
    @Query(value = "select f from Floor f where f.building.buildingName = ?1")
    List<Floor> findByBuilding(String buildingName);
}
