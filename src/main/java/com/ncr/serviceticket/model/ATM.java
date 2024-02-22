package com.ncr.serviceticket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "atms")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atm {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    @Column(name = "atm_id", nullable = false, unique = true, length = 10)
    private String atmId;
    @Column(name = "serial_no", nullable = false, unique = true, length = 20)
    private String serialNo;
    @Column(name = "client_name", nullable = false, length = 20)
    private String clientName;
    @Column(name = "type", nullable = false, length = 20)
    private String type;
    @Column(name = "location", nullable = false, unique = true, length = 50)
    private String location;
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;
}
