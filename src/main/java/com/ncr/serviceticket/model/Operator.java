package com.ncr.serviceticket.model;

import com.ncr.serviceticket.validation.annotations.ContactNumberConstraint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    @NotNull
    @Size(max = 50)
    private String name;

    @Column(name = "role")
    @NotBlank
    @NotNull
    @Size(max = 50)
    private String role;

    @Column(name = "phone")
    @NotBlank
    @NotNull
    @Size(max = 50)
    @ContactNumberConstraint
    private String phone;

    @Column(name = "email")
    @NotBlank
    @NotNull
    @Size(max = 32)
    private String email;

    @Column(name = "password")
    @NotBlank
    @NotNull
    @Size(max = 200)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "operator_roles", joinColumns = @JoinColumn(name = "operator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<AuthorizationPosition> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "operator_messages", joinColumns = @JoinColumn(name = "operator_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "message_id", referencedColumnName = "id"))
    private List<MessagePattern> messages = new ArrayList<>();
}