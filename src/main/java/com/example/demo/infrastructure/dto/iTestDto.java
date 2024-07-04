package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.entity.Test;
import java.util.List;
import java.util.Optional;

public interface  iTestDto
 {

     List<Test> findAll();
     Optional<Test> findById(Long id);
    Optional<Test> save(Test test);
     void deleteById(Long id);

}
