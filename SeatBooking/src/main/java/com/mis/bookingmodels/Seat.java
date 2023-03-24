package com.mis.bookingmodels;

import jakarta.persistence.*;

@Entity
@Table(name = "Seat")
public class Seat {
    @Id
    private Integer seatNo;
    private int floorNo;

    public Seat(Integer seatNo, int floorNo, String buildingName, String startDate, String startTime, String endTime, String endDate, Room room) {
        this.seatNo = seatNo;
        this.floorNo = floorNo;
        this.buildingName = buildingName;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.endDate = endDate;
        this.room = room;
    }

    private String buildingName;
    private String startDate;
    private String startTime;
    private String endTime;
    private String endDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roomNo")
    Room room;

    public Seat() {

    }

    @Override
    public String toString() {
        return "Seat{" +
                "seatNo=" + seatNo +
                ", floorNo=" + floorNo +
                ", buildingName='" + buildingName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", endDate='" + endDate + '\'' +
                ", room=" + room +
                '}';
    }

    public Integer getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(Integer seatNo) {
        this.seatNo = seatNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
