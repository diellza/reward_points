package com.retailer.task.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * TransactionEntity
 */
@Entity
@Table(name = "transaction")
@Getter
@Setter
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime;

    @Column(name = "total_purchase_price")
    private double totalPurchasePrice;

    @Column(name = "month")
    private Integer month;

    /**
     * Reward points calculated per transaction
     */
    @Column(name = "reward_points")
    private Integer rewardPoints;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
