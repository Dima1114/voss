package com.example.demo.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ScheduledExecutorService;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
public class SchedulerServiceTest {

    @MockBean ResponseService responseService;
    @Autowired ScheduledExecutorService scheduledExecutorService;

    SchedulerService schedulerService;

    @BeforeEach
    void setUp() {
        schedulerService = new SchedulerService(scheduledExecutorService, responseService);
        schedulerService.setPollingPeriod(100);
    }

    @Test
    void shouldRunTaskMultipleTimes() throws InterruptedException {

        schedulerService.scheduleDataProcessing();

        Thread.sleep(500);

        verify(responseService, atLeast(5)).callApiAndSaveResponse();
    }

}
