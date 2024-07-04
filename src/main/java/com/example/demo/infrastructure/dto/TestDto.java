package com.example.demo.infrastructure.dto;

import org.springframework.stereotype.Component;

import com.example.demo.infrastructure.repository.TestRepository;

@Component
public class TestDto {

private final TestRepository repository;

public TestDto( TestRepository repository ) {
this.repository = repository;}

}