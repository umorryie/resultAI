package com.example.demo;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = DatesController.class)
class DatesRepositoryControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private DatesRepository datesRepository;

  @Test
  void WhenValidInputOnCreateDate_ShouldInsertDateInPostgres() throws Exception {
    // Arrange
    DateObject user = new DateObject("test", new Date(0));
    when(datesRepository.save(Mockito.any(DateObject.class))).thenReturn(user);

    // Act and arrange
	mockMvc.perform(post("/api/javainuse/dates/create", 42L)
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.dayName", is("test")))
		.andExpect(jsonPath("$.dayDate", is("1970-01-01")));
  }

  @Test
  void ShouldReturnAllDatesFromPostgres() throws Exception {
    // Arrange
    DateObject user = new DateObject("test", new Date(0));
	List<DateObject> allDates = new ArrayList<DateObject>();
    allDates.add(user);

    when(datesRepository.findAll()).thenReturn(allDates);

    // Act and assert
	mockMvc.perform(get("/api/javainuse/allDates", 42L)
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(user)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].dayName", is("test")))
		.andExpect(jsonPath("$[0].dayDate", is("1970-01-01")));
  }

  @Test
  public void ShouldReturnHelloWorld() {
    // Arrange
    DatesController datesController = new DatesController();

    // Act
    String result = datesController.GetHelloWorld();

    // Assert
    assertEquals(result, "Hello world.");
  }

  @Test
  void ShouldReturn_HelloWorld() throws Exception {
    // Act and assert
	String response = mockMvc.perform(get("/api/javainuse/helloWorld")
		.contentType("application/json"))
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString();

    assertEquals("Hello world.", response);
  }
  
  @Test
  void CreateRequest_DatabaseMethodSaveShouldBeCalled() throws Exception {
	   // Arrange
	    DateObject user = new DateObject("test", new Date(0));
	    when(datesRepository.save(Mockito.any(DateObject.class))).thenReturn(user);

	    // Act and arrange
		mockMvc.perform(post("/api/javainuse/dates/create", 42L)
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)));
		verify(datesRepository, times(1)).save(Mockito.any(DateObject.class));
  }
  
  @Test
  void CreateRequest_ControllerMethodCreateDatesShouldBeCalled() throws Exception {
	   // Arrange
	    DateObject user = new DateObject("test", new Date(0));
	    when(datesRepository.save(Mockito.any(DateObject.class))).thenReturn(user);

	    // Act and arrange
		mockMvc.perform(post("/api/javainuse/dates/create", 42L)
			.contentType("application/json")
			.content(objectMapper.writeValueAsString(user)))
		    .andExpect(handler().handlerType(DatesController.class))
		    .andExpect(handler().methodName("CreateDates"));
  }
}