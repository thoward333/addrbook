package com.trey.controller;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.trey.addrbook.controller.PersonController;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonController {

	private MockMvc mockMvc;

	@Mock
	PersonService personService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new PersonController(personService)).build();
	}

	@Test
	public void testSuccess() throws Exception {
		Person person = new Person();
		person.setId(1);
		person.setUsername("thoward333");
		person.setFirstName("Trey");
		person.setLastName("Howard");
		when(personService.getPersonById(anyInt())).thenReturn(person);

		mockMvc.perform(post("/person/{id}", 1L).accept(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andReturn();
	}

}
