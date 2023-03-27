package com.mis.bookingrepositories;

import com.mis.bookingmodels.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepo extends JpaRepository<Seat, Long> {
    @Query(value = "select s from Seat s where s.room.roomNo = ?1 and s.floorNo = ?2 and s.buildingName = ?3")
    Optional<List<Seat>> getAllSeats(Integer roomNo, Integer floorNo, String buildingName);
}
