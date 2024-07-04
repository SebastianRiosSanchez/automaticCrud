package com.example.demo.infrastructure.dto;

import com.example.demo.infrastructure.entity.Data;
import java.util.List;
import java.util.Optional;

public interface  iDataDto
 {

     List<Data> findAll();
     Optional<Data> findById(Long id);
    Optional<Data> save(Data data);
    public void deleteById(Long id);

}
