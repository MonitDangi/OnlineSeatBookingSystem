package com.mis.bookingmodels;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long bookingId;

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", userinfo=" + userinfo +
                ", buildingList=" + buildingList +
                '}';
    }

    public Booking() {

    }

    public Long getBookingId() {
        return bookingId;
    }

    public Booking(Long bookingId, String startDate, String endDate, User userinfo, List<Building> buildingList) {
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userinfo = userinfo;
        this.buildingList = buildingList;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }

    public List<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<Building> buildingList) {
        this.buildingList = buildingList;
    }

    private String startDate;
    private String endDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    User userinfo;
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Building>buildingList;
}
