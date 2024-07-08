package com.example.demo.infrastructure.service;

import com.example.demo.infrastructure.entity.Test;
import com.example.demo.infrastructure.repository.TestRepository;
import com.example.demo.infrastructure.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService implements iTestService {

    private final TestDto testDto;

    @Autowired
    public TestService(TestDto testDto) {

        this.testDto = testDto;
    }

    @Override
    public Test save(Test testDto) {
        return this.testDto.save(testDto);
    }

    @Override
    public List<Test> findAll() {
        return this.testDto.findAll();
    }

    @Override
    public Optional<Test> findById(Long id) {
        return this.testDto.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        testDto.deleteById(id);
    }

}
