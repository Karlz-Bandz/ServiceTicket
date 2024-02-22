package com.ncr.serviceticket.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @Column(name = "atm_id")
    @NotBlank
    @Size(min = 8, max = 10)
    private String atmId;

    @Column(name = "serial_no")
    @NotBlank
    @Size(min = 8, max = 20)
    private String serialNo;

    @Column(name = "client_name")
    @NotBlank
    @Size(max = 20)
    private String clientName;

    @Column(name = "type")
    @NotBlank
    @Size(max = 20)
    private String type;

    @Column(name = "location")
    @NotBlank
    @Size(max = 50)
    private String location;

    @Column(name = "phone")
    @NotBlank
    @Size(max = 20)
    private String phone;
}
