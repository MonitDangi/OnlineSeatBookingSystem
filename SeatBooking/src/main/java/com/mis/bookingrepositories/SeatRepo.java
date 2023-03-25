package com.mis.bookingrepositories;

import com.mis.bookingmodels.Room;
import com.mis.bookingmodels.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface SeatRepo extends JpaRepository<Seat, Long> {
}
