package com.retailer.task.scheduler;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;
import com.retailer.task.domain.TransactionEntity;
import com.retailer.task.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class RewardPointsCalculationSchedulerTest {

    @Mock
    private Appender mockAppender;

    @Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardPointsCalculationScheduler rewardPointsCalculationScheduler;

    @Test
    public void testCalculatePoints() {
        final Logger debugLogger = (Logger) LoggerFactory.getLogger(RewardPointsCalculationScheduler.class);
        debugLogger.addAppender(mockAppender);
        debugLogger.setLevel(Level.INFO);
        TransactionEntity transaction = new TransactionEntity();
        transaction.setMonth(11);
        transaction.setTotalPurchasePrice(120);
        doReturn(Arrays.asList(transaction)).when(transactionRepository).findAllByRewardPointsIsNullAndTotalPurchaseMore50();
        rewardPointsCalculationScheduler.calculatePoints();
        assertEquals(90, transaction.getRewardPoints());
        verify(mockAppender, times(2)).doAppend(captorLoggingEvent.capture());
        assertEquals("The scheduler for calculating reward points is starting for month {}", captorLoggingEvent.getAllValues().get(0).getMessage());
    }
}
