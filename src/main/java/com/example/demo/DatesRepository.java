package com.example.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
 
public interface DatesRepository extends CrudRepository<DateObject, Long> {
}