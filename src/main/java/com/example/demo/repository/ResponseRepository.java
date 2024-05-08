package com.example.demo.repository;

import com.example.demo.model.ResponseDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<ResponseDomain, Long> {
}
