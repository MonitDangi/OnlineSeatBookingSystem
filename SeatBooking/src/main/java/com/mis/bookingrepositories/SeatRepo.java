package com.mis.bookingrepositories;

import com.mis.bookingmodels.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepo extends JpaRepository<Seat, Integer> {
    @Query(value = "select s from Seat s where s.floorNo=?1")
    Optional<List<Seat>> getAllSeats(Integer floorNo);
    @Query(value = "select s.seatId from Seat s where s.seatNo=?1 and s.floorNo=?2 and s.buildingName=?3 and s.room.roomNo=?4")
    Integer findId(Integer seatNo, Integer floorNo, String buildingName, Integer roomNo);
    @Query(value = "select s from Seat s")
    List<Seat> getAllSeat();
}
