package com.trey.addrbook.controller;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trey.addrbook.controller.PersonController;
import com.trey.addrbook.controller.fixture.ControllerTestFixture;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.dto.save.SavePersonRequest;
import com.trey.addrbook.exception.PersonNotFoundException;
import com.trey.addrbook.service.PersonService;
import com.trey.addrbook.util.DtoFactory;

/**
 * Unit tests the controller, including JSON serialization.
 * 
 * @author Trey
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPersonController {

	private MockMvc mockMvc;

	@Mock private PersonService personService;

	@Before
	public void setUp() {
		DtoFactory personDtoFactory = new DtoFactory();
		mockMvc = MockMvcBuilders.standaloneSetup(new PersonController(personService, personDtoFactory)).build();
	}

	@Test
	public void test_getPersonById() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();
		when(personService.getPersonById(anyInt())).thenReturn(person);

		mockMvc.perform(get("/person/{id}", 1)
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.fullname", is(person.getFirstName() + " " + person.getLastName())))
				.andReturn();
	}

	@Test
	public void test_getPersonById_NotFound() throws Exception {
		final String errorMessage = "Mocking 404 message";
		when(personService.getPersonById(anyInt())).thenAnswer(new Answer<Person>() {
			public Person answer(InvocationOnMock invocation) throws Throwable {
				throw new PersonNotFoundException(errorMessage);
			}
		});

		mockMvc.perform(get("/person/{id}", -1)
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isNotFound())
				.andExpect(content().string(errorMessage))
				.andReturn();
	}

	@Test
	public void test_getPersonByIdFromParam() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();
		when(personService.getPersonById(anyInt())).thenReturn(person);

		mockMvc.perform(get("/person?id={id}", 1)
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.fullname", is(person.getFirstName() + " " + person.getLastName())))
				.andReturn();
	}

	@Test
	public void test_savePerson() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();
		final Integer newId = person.getId();
		person.setId(null);
		
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				Person p = (Person) args[0];
				p.setId(newId); // emulate the successful save populating the id
	            return "called with arguments: " + args;
			}
		}).when(personService).savePerson((Person) anyObject());

		SavePersonRequest spr = new SavePersonRequest();
		spr.setUserName(person.getUserName());
		spr.setFirstName(person.getFirstName());
		spr.setLastName(person.getLastName());
		
		mockMvc.perform(post("/person")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(spr))
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(content().string(newId.toString()))
				.andReturn();
	}
	
}

