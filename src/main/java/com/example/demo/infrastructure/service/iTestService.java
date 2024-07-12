package com.example.demo.infrastructure.service;

import com.example.demo.infrastructure.entity.Test;

import java.util.List;
import java.util.Optional;

public interface iTestService {

    Test save(Test test);

    List<Test> findAll();

    Optional<Test> findById(Long id);

    void deleteById(Long id);

    Test update(Test test) throws Exception;

}