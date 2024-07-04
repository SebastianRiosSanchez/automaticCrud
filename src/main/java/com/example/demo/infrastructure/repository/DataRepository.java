package com.example.demo.infrastructure.repository;

import com.example.demo.infrastructure.entity.Data;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface DataRepository extends JpaRepository<Data,Long> {
}
