package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class VossApiClient {

    private final RestClient restClient = RestClient.create();

    @Value("${voss.baseUrl}")
    private String baseUrl;

    public ResponseDto getData() {
        return restClient.get()
                .uri(baseUrl + "/api/v1/data")
                .retrieve()
                .body(ResponseDto.class);
    }
}
