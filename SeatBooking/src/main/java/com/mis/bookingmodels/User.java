package com.mis.bookingmodels;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String name;
    private String number;
    private String userEmail;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Booking>bookingList;
}
