package com.retailer.task.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * CustomerEntity
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long customerId;

    private String name;

    private String surname;

    private String phone;

    private String address;

    @OneToMany(mappedBy = "customer")
    private List<TransactionEntity> transactionEntities;
}
