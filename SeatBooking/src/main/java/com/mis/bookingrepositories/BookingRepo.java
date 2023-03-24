package com.mis.bookingrepositories;

import com.mis.bookingmodels.Booking;
import com.mis.bookingmodels.Building;
import com.mis.bookingmodels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,String> {
    @Query(value = "select u from User u where u.userId = ?1 and u.password = ?2")
    Optional<User> validateUser(String userName, String userPassword);
    @Query(value = "select b from Building b where b.location = ?1")
    List<Building> findAvailableSeatsAtLocation(String location);
}
