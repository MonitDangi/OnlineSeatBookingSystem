package com.mis.bookingrepositories;

import com.mis.bookingmodels.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room, Long> {
}