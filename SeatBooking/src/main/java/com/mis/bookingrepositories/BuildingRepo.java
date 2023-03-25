package com.mis.bookingrepositories;

import com.mis.bookingmodels.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuildingRepo extends JpaRepository<Building, String> {

}
