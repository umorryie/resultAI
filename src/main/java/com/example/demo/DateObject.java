package com.example.demo;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "dates")
public class DateObject {
 
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique=true)
  private long id;
 
  @Column(name = "dayName")
  private String dayName;
  
  @Column(name = "dayDate")
  private Date dayDate;
 
  public DateObject() {
  }
 
  public DateObject(String dayName, Date dayDate) {
    this.dayName = dayName;
    this.dayDate = dayDate;
  }
 
  public long getId() {
    return id;
  }
 
  public void setDayName(String dayName) {
    this.dayName = dayName;
  }
 
  public String getDayName() {
    return this.dayName;
  }
  
  public void setDayDate(Date dayDate) {
	    this.dayDate = dayDate;
	  }
	 
  public Date getDayDate() {
    return this.dayDate;
  }
}
