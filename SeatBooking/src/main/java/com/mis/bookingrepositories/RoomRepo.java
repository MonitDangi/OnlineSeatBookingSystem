package com.mis.bookingrepositories;

import com.mis.bookingmodels.Floor;
import com.mis.bookingmodels.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    @Query(value = "select r  from Room r where r.roomNo = ?1 and r.floor = ?2 and r.buildingName = ?3")
    Optional<Room> checkRoom(Integer roomNo, Floor floor, String buildingName);
}
