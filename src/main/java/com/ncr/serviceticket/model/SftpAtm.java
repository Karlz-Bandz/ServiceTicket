package com.ncr.serviceticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sftp_atm")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SftpAtm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column(name = "user_name")
    @NotNull
    @Size(max = 100)
    private String userName;

    @Column(name = "host_name")
    @NotNull
    @Size(max = 100)
    private String hostName;

    @Column(name = "password")
    @Size(max = 100)
    private String password;

    @Column(name = "port")
    @NotNull
    private int port;

    @OneToOne
    @JoinColumn(name = "atm_id")
    private Atm atm;
}
