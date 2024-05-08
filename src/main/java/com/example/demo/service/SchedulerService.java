package com.example.demo.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(value = "voss.enableScheduler")
public class SchedulerService {

    @Setter
    @Value("${voss.pollingPeriod}")
    private long pollingPeriod;

    private final ScheduledExecutorService scheduledExecutorService;
    private final ResponseService responseService;

    @PostConstruct
    public void scheduleDataProcessing() {
        log.info("start data collection with delay={} seconds", pollingPeriod);
        scheduledExecutorService.scheduleAtFixedRate(responseService::callApiAndSaveResponse, 0, pollingPeriod, TimeUnit.MILLISECONDS);
    }
}
