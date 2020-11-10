package com.example.demo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/javainuse")
public class DatesController {
 
  @Autowired
  DatesRepository repository;

  @GetMapping("/helloWorld")
  public String GetHelloWorld() {
    return "Hello world.";
  }
  
  @GetMapping("/allDates")
  public List<DateObject> allDates() {
    return (List<DateObject>) repository.findAll();
  }
  
  @PostMapping("/getDateById")
  public Optional<DateObject> GetDateById(@RequestBody long id) {
	  return repository.findById(id);
  }

  @PostMapping(value = "/dates/create")
  public DateObject CreateDates(@RequestBody DateObject dateTime) {
		DateObject preparedDate = repository.save(new DateObject(dateTime.getDayName(), dateTime.getDayDate()));
	    return preparedDate;
  }
}