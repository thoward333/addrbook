package com.trey.addrbook.dao;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.trey.addrbook.domain.Person;
import com.trey.addrbook.exception.PersonNotFoundException;
import com.trey.controller.fixture.PersonTestFixture;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonDaoImpl {

	private PersonDaoImpl personDao;

	@Before
	public void setUp() throws Exception {
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:test-init.sql").build();

		personDao = new PersonDaoImpl(dataSource);
	}

	@Test
	public void test_findById() {
		Integer id = 1;
		Person p1 = personDao.findById(id);
		assertEquals(p1.getId(), id);
	}

	@Test(expected = PersonNotFoundException.class)
	public void test_findById_NotFound() {
		Integer id = -1;
		personDao.findById(id);
	}
	
//	@Test
//	public void test_insert() {
//		PersonTestFixture f = new PersonTestFixture();
//		Person person = f.createPerson();
//		
//		// function not supported!? the generated key stuff...
//		personDao.insert(person);
//	}
	
	@Test
	public void test_update() {
		PersonTestFixture f = new PersonTestFixture();
		Person person = f.createPerson();
		
		String newFirstName = "Fred";
		
		person.setFirstName(newFirstName);
		personDao.update(person);
		
		Person updatedPerson = personDao.findById(person.getId());
		assertEquals(newFirstName, updatedPerson.getFirstName());
	}

}
