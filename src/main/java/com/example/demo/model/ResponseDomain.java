package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "response")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDomain {

    @Id
    @GeneratedValue
    private Long id;

    private long responseId;

    private long dataLength;

    @Column(columnDefinition = "TIMESTAMP(6) WITHOUT TIME ZONE")
    private Instant callTime;
}
