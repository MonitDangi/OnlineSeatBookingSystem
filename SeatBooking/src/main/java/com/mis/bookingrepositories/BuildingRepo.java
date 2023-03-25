package com.mis.bookingrepositories;

import com.mis.bookingmodels.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends JpaRepository<Building, String> {
}
