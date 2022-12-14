package com.retailer.task.repository;

import com.retailer.task.domain.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * RewardPoints Repository
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    /**
     * Fetch customer, months and sum of reward points(calculated by scheduler) for last 3 months for customers
     * not including the current one
     *
     * @return
     */
    @Query(value = "SELECT transaction.customer.customerId, transaction.month, sum(transaction.rewardPoints) " +
            "FROM TransactionEntity transaction JOIN transaction.customer customer " +
            "where transaction.rewardPoints is not null and transaction.month IN(:month, :month-1, :month-2)" +
            "group by transaction.customer, transaction.month")
    List<Object[]> findRewardPointsCustomerPerMonth(int month);

    /**
     * Fetch all transactions that have total_purchase_price greater than 50
     *
     * @return
     */
    @Query(value = "SELECT transaction " +
            "FROM TransactionEntity transaction  " +
            "where transaction.rewardPoints is null and transaction.totalPurchasePrice >=50")
    List<TransactionEntity> findAllByRewardPointsIsNullAndTotalPurchaseMore50();

    /**
     * Fetch all customer and sum of reward points for customer, for all months(not inculding the current one)
     *
     * @return
     */
    @Query(value = "SELECT transaction.customer.customerId, sum(transaction.rewardPoints) " +
            "FROM TransactionEntity transaction JOIN transaction.customer customer " +
            "where transaction.rewardPoints is not null " +
            "group by transaction.customer")
    List<Object[]> findRewardPointsCustomerTotal();

}
