package com.mis.bookingrepositories;

import com.mis.bookingmodels.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepo extends JpaRepository<Seat, Long> {
}
