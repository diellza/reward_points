package com.retailer.task.domain.dto;

import lombok.Data;

@Data
public class RewardPointsMonthDTO {

    private Long customerId;
    private Long totalRewardPoints;
    private Integer month;

}
