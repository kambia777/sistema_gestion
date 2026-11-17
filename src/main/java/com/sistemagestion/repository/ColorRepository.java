package com.sistemagestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemagestion.entity.Color;


public interface ColorRepository extends JpaRepository<Color, Long> {

}
