package com.trey.addrbook.dao;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.trey.addrbook.dao.fixture.DaoImplTestFixture;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.exception.PersonNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class TestPersonDaoImpl {

	private PersonDaoImpl personDao;

	@Before
	public void setUp() throws Exception {
		DataSource dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:init.sql").build();

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
	
	@Test
	public void test_insert() {
		DaoImplTestFixture f = new DaoImplTestFixture();
		Person person = f.createTrey();
		
		// function not supported!? the generated key stuff...
		personDao.insert(person);
	}
	
	@Test
	public void test_update() {
		DaoImplTestFixture f = new DaoImplTestFixture();
		Person person = f.createTrey();
		
		String newFirstName = "Fred";
		
		person.setFirstName(newFirstName);
		personDao.update(person);
		
		Person updatedPerson = personDao.findById(person.getId());
		assertEquals(newFirstName, updatedPerson.getFirstName());
	}

}
