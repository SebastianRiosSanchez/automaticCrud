package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.entity.Test;
import java.util.List;
import java.util.Optional;

public interface  iTestDto
 {

Test save(Test test);
     List<Test> findAll();
     Optional<Test> findById(Long id);
     void deleteById(Long id);

}
