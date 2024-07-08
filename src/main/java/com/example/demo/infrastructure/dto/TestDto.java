package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.entity.Test;
import org.springframework.stereotype.Component;

import com.example.demo.infrastructure.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TestDto  implements  iTestDto{

private final TestRepository repository;

public TestDto( TestRepository repository ) {
this.repository = repository;
}

@Override
public Test save(Test test) {
return repository.save(test);
}

@Override
public List<Test> findAll() {
return repository.findAll();
}

@Override
public Optional<Test> findById(Long id) {
return repository.findById(id);
}

@Override
public void deleteById(Long id) {
repository.deleteById(id);
}

}