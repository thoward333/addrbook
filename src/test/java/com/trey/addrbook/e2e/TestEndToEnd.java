package com.trey.addrbook.e2e;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trey.addrbook.controller.PersonController;
import com.trey.addrbook.controller.TestUtil;
import com.trey.addrbook.controller.fixture.ControllerTestFixture;
import com.trey.addrbook.dao.PersonDao;
import com.trey.addrbook.dao.PersonDaoImpl;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.dto.save.SavePersonRequest;
import com.trey.addrbook.service.PersonService;
import com.trey.addrbook.service.PersonServiceImpl;
import com.trey.addrbook.util.DtoFactory;

// TODO convert to use Spring's JUnitRunner so we don't have to manually define spring context
@RunWith(MockitoJUnitRunner.class)
public class TestEndToEnd {
	
	private MockMvc mockMvc;
	private DataSource dataSource;

	@Before
	public void setUp() {
		dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:init.sql").build();

		PersonDao personDao = new PersonDaoImpl(dataSource);
		
		PersonService personService = new PersonServiceImpl(personDao);
		
		DtoFactory personDtoFactory = new DtoFactory();
		mockMvc = MockMvcBuilders.standaloneSetup(new PersonController(personService, personDtoFactory)).build();
	}

	@Test
	public void test_getPersonById() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();

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
		Integer badId = -1;
		mockMvc.perform(get("/person/{id}", badId)
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isNotFound())
				.andExpect(content().string("No person found for id: " + badId))
				.andReturn();
	}

	@Test
	public void test_getPersonByIdFromParam() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();

		mockMvc.perform(get("/person?id={id}", 1)
				.accept(TestUtil.APPLICATION_JSON_UTF8)
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.fullname", is(person.getFirstName() + " " + person.getLastName())))
				.andReturn();
	}

	// complications with generated keys
	@Test
	public void test_savePerson() throws Exception {
		ControllerTestFixture f = new ControllerTestFixture();
		Person person = f.createTrey();
		person.setId(null);

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
				.andExpect(content().string("4")) // init script creates 3 records
				.andReturn();
	}

}
