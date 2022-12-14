package com.retailer.task.scheduler;


import com.retailer.task.domain.TransactionEntity;
import com.retailer.task.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Setter
@Slf4j
@Component
@RequiredArgsConstructor
public class RewardPointsCalculationScheduler {

    private final TransactionRepository rewardPointsRepository;

    /**
     * Cron job to be executed at 00:00 every first day of month,
     * to calculate the reward points for the previous month for every transaction of that month
     */
    //@Scheduled(cron = "0 0 0 1 * *")
    @Scheduled(cron = "0 */2 * * * *")
    public void calculatePoints() {
        List<TransactionEntity> transactionListPerMonth = rewardPointsRepository.findAllByRewardPointsIsNullAndTotalPurchaseMore50();
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        log.info("The scheduler for calculating reward points is starting for month {}", month);
        for (TransactionEntity transaction : transactionListPerMonth) {
            Integer totalRewardPoints = calculateRewardPoints(transaction.getTotalPurchasePrice()).intValue();
            transaction.setRewardPoints(totalRewardPoints);
            log.debug("Total reward points {}", totalRewardPoints);
            transaction.setMonth(month);
            rewardPointsRepository.save(transaction);
        }
    }

    private Double calculateRewardPoints(double totalPurchasePrice) {
        if (totalPurchasePrice >= 100) {
            return 2 * (totalPurchasePrice - 100) + 50;
        }
        return (totalPurchasePrice - 50) * 1;
    }
}
