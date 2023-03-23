package com.mis.bookingmodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "floor")
public class Floor {
    @Id
    private Integer floorNo;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="buildingName")
    Building building;
    @OneToMany(mappedBy = "floor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Room>roomList;


}
