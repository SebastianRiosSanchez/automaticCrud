package com.example.demo.infrastructure.controller;

import com.example.demo.infrastructure.Test;
import com.example.demo.infrastructure.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public List<Test> getAllTest() {
        return testService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable Long id) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {
            return ResponseEntity.ok(test.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.save(test);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> updateTest(@PathVariable Long id, @RequestBody Test testDetails) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {
            Test updatedTest = test.get();
            updatedTest.setTestName(testDetails.getTestName());
            return ResponseEntity.ok(testService.save(updatedTest));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTest(@PathVariable Long id) {
        Optional<Test> test = testService.findById(id);
        if (test.isPresent()) {
            testService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
