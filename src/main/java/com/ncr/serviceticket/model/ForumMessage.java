package com.ncr.serviceticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "forum")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private long id;

    @Column(name = "message")
    @Size(max = 10000)
    private String message;

    @Column(name = "date")
    @Size(max = 100)
    @NotNull
    private String date;

    @ManyToOne
    @JoinColumn(name = "operator_id")
    private Operator forumOperator;
}
