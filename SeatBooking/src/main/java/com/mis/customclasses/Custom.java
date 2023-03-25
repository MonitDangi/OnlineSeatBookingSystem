package com.mis.customclasses;

import com.mis.bookingmodels.*;

public class Custom {
    User user;
    Booking booking;
    Building building;
    Floor floor;
    Room room;

    public Custom() {
    }

    public Custom(User user){
        this.user = user;
    }
    public Custom(User user, Booking booking){
        this.user = user;
        this.booking = booking;
    }
    public Custom(User user, Booking booking, Building building){
        this.user = user;
        this.booking = booking;
        this.building = building;
    }
    public Custom(User user, Booking booking, Building building, Floor floor){
        this.user = user;
        this.booking = booking;
        this.building = building;
        this.floor = floor;
    }
    public Custom(User user, Booking booking, Building building, Floor floor, Room room){
        this.user = user;
        this.booking = booking;
        this.building = building;
        this.floor = floor;
        this.room = room;
    }


    public String getBuildingName() {
        return building.getBuildingName();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getBuildingLocation() {
        return building.getLocation();
    }

    public String getUserId() {
        return user.getUserId();
    }

    public String getUserPassword() {
        return user.getPassword();
    }
}
