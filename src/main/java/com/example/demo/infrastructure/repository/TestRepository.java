package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.Test;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface TestRepository extends JpaRepository<Test,Long> {
}
