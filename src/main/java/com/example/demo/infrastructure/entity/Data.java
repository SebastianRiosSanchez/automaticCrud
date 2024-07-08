package com.example.demo.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_data", nullable = false)
    private Long idData;

    @Column(name = "name_data", length = 25)
    private String nameData;

    @Column(name = "desc_data", length = 100)
    private String descData;

}