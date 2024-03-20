package com.ncr.serviceticket.model;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import com.ncr.serviceticket.validation.annotations.UpperCaseConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Size(min = 8, max = 10)
    @UpperCaseConstraint
    private String atmId;

    @Column(name = "serial_no")
    @NotBlank
    @NotNull
    @Size(min = 8, max = 20)
    private String serialNo;

    @Column(name = "client_name")
    @NotBlank
    @NotNull
    @Size(max = 120)
    private String clientName;

    @Column(name = "type")
    @NotBlank
    @NotNull
    @Size(max = 20)
    @UpperCaseConstraint
    private String type;

    @Column(name = "location")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String location;

    @Column(name = "phone")
    @NotBlank
    @NotNull
    @Size(max = 20)
    @ContactNumberConstraint
    private String phone;
}
