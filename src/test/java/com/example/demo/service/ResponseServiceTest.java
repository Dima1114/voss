package com.example.demo.service;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.ResponseDomain;
import com.example.demo.repository.ResponseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@SpringBootTest
@ActiveProfiles("test")
class ResponseServiceTest {

    @MockBean VossApiClient vossApiClient;
    @Autowired ResponseService responseService;
    @Autowired ResponseRepository responseRepository;

    @AfterEach
    void cleanup() {
        responseRepository.deleteAll();
    }

    @Test
    void shouldSaveOneNewResponse() {

        Instant now = Instant.now().truncatedTo(ChronoUnit.MILLIS);
        ResponseDto responseData = new ResponseDto(12345L, now, "test response data");
        doReturn(responseData)
                .doThrow(new RuntimeException("error call"))
                .when(vossApiClient).getData();

        responseService.callApiAndSaveResponse();
        responseService.callApiAndSaveResponse();

        List<ResponseDomain> responses = responseRepository.findAll();
        assertEquals(1, responses.size());
        assertEquals(12345L, responses.getFirst().getResponseId());
        assertEquals(now, responses.getFirst().getCallTime());
        assertEquals(responseData.data().length(), responses.getFirst().getDataLength());
    }

}
