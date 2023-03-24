package com.mis.customclasses;
public class Custom {
    private String userId;
    private String userPassword;
    private String location;
    private String buildingName;
    private int floorNumber;
    private int roomNo;
    private int seatNo;


    public Custom() {
    }

    public Custom(String userId, String userPassword, String location) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getLocation() {
        return location;
    }
}
