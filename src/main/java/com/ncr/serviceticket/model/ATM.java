package com.ncr.serviceticket.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "atms")
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "atm_id", nullable = false, length = 10)
    private String atmId;
    @Column(name = "serial_no", nullable = false, length = 20)
    private String serialNo;
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    @Column(name = "location", nullable = false, length = 50)
    private String location;
    @Column(name = "phone", nullable = false, length = 20)
    private int phone;
    @Column(name = "date", nullable = false)
    private Date currentDate;
    @Column(name = "description", nullable = false, length = 1500)
    private String description;
}
