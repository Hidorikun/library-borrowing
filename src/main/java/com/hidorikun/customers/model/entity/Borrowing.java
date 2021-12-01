package com.hidorikun.customers.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long customerId;

    private Long bookId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date borrowedOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedOn;

}
