package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.mapper.ResponseMapper;
import com.example.demo.model.ResponseDomain;
import com.example.demo.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final VossApiClient vossApiClient;
    private final ResponseMapper responseMapper;

    public void callApiAndSaveResponse() {
        try {
            log.info("call voss at: {}", Instant.now());
            ResponseDto data = vossApiClient.getData();
            ResponseDomain responseDomain = responseMapper.fromDto(data);
            responseRepository.save(responseDomain);
        } catch (Exception ex) {
            log.error("data collection request failed", ex);
        }
    }
}
