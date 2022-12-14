package com.retailer.task.controller;

import com.retailer.task.domain.dto.RewardPointsMonthDTO;
import com.retailer.task.domain.dto.RewardPointsTotalDTO;
import com.retailer.task.service.RewardPointsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller to access two endpoints for reward points
 */
@RequestMapping("/reward/points")
@Slf4j
@RestController
@RequiredArgsConstructor
public class RewardPointsController {

    private final RewardPointsService rewardPointsService;

    /**
     * Get the reward points for each customer per month
     *
     * @return
     */
    @GetMapping("/month")
    public ResponseEntity<List<RewardPointsMonthDTO>> getRewardPointsByCustomer() {
        log.info("Reward Points By Customer per month is called.");
        return ResponseEntity.ok(rewardPointsService.getRewardPointsEachCustomer());
    }

    /**
     * Get the reward point per each customer total
     *
     * @return
     */
    @GetMapping("/total")
    public ResponseEntity<List<RewardPointsTotalDTO>> getRewardPointsPerCustomerTotal() {
        log.info("Reward Points By Customer total is called.");
        return ResponseEntity.ok(rewardPointsService.getRewardPointsTotal());
    }
}
