package com.mis.bookingmodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Building {
    @Id
    private String buildingName;
    private String location;
    int numberOfSeatsAvailable;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingId")
    Booking booking;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Floor>floorList;

}
