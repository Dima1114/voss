package com.example.demo.mapper;

import com.example.demo.dto.ResponseDto;
import com.example.demo.model.ResponseDomain;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public ResponseDomain fromDto(ResponseDto dto) {
        return ResponseDomain.builder()
                .responseId(dto.id())
                .callTime(dto.date())
                .dataLength(dto.data().length())
                .build();
    }
}
