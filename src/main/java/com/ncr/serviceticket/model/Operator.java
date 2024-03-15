package com.ncr.serviceticket.model;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "operators")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Operator {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank
    @Size(max = 50)
    private String name;

    @Column(name = "role")
    @NotBlank
    @Size(max = 50)
    private String role;

    @Column(name = "phone")
    @NotBlank
    @Size(max = 50)
    @ContactNumberConstraint
    private String phone;
}