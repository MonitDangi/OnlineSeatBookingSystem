package com.mis.bookingmodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "room")
public class Room {
    @Id
    private Integer roomNo;
    private String buildingName;
    private int numberOfSeats;

    public Room(Integer roomNo, String buildingName, int numberOfSeats, Floor floor, List<Seat> seatList) {
        this.roomNo = roomNo;
        this.buildingName = buildingName;
        this.numberOfSeats = numberOfSeats;
        this.floor = floor;
        this.seatList = seatList;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "floorNo")
    Floor floor;
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Seat>seatList;

    public Room() {

    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", buildingName='" + buildingName + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", floor=" + floor +
                ", seatList=" + seatList +
                '}';
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}
