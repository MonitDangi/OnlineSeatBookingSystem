package com.mis.bookingmodels;

import jakarta.persistence.*;

@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    private Integer seatNo;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roomNo")
    Room room;
}
