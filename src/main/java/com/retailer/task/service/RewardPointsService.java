package com.retailer.task.service;

import com.retailer.task.domain.dto.RewardPointsMonthDTO;
import com.retailer.task.domain.dto.RewardPointsTotalDTO;
import com.retailer.task.exceptions.NotFoundException;
import com.retailer.task.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Service to calculate the reward points
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RewardPointsService {

    private final TransactionRepository rewardPointsRepository;

    /**
     * Return reward points for each customer per month - for 3 months
     *
     * @return List<RewardPointsTotalDTO>
     */
    public List<RewardPointsMonthDTO> getRewardPointsEachCustomer() {
        List<RewardPointsMonthDTO> rewardPointsTotalDTOS = new ArrayList<>();
        int month = Calendar.getInstance().get(Calendar.MONTH);
        List<Object[]> customerEntityList = rewardPointsRepository.findRewardPointsCustomerPerMonth(month);
        if (customerEntityList.isEmpty())
            throw new NotFoundException();
        for (Object[] row : customerEntityList) {
            RewardPointsMonthDTO rewardPointsTotalDTO = new RewardPointsMonthDTO();
            rewardPointsTotalDTO.setCustomerId((Long) row[0]);
            rewardPointsTotalDTO.setMonth((Integer) row[1]);
            rewardPointsTotalDTO.setTotalRewardPoints((Long) row[2]);
            rewardPointsTotalDTOS.add(rewardPointsTotalDTO);
        }
        return rewardPointsTotalDTOS;
    }

    /**
     * Return reward points for each customer total
     *
     * @return List<RewardPointsTotalDTO>
     */
    public List<RewardPointsTotalDTO> getRewardPointsTotal() {
        List<RewardPointsTotalDTO> responseDTOList = new ArrayList<>();
        List<Object[]> transactionList = rewardPointsRepository.findRewardPointsCustomerTotal();
        for (Object[] transaction : transactionList) {
            RewardPointsTotalDTO responseDTO = new RewardPointsTotalDTO();
            responseDTO.setCustomerId((Long) transaction[0]);
            responseDTO.setTotalRewardPoints((Long) transaction[1]);
            responseDTOList.add(responseDTO);
        }
        return responseDTOList;
    }
}
