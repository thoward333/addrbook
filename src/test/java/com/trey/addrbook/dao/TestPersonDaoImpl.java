package com.trey.addrbook.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.trey.addrbook.dao.fixture.DaoImplTestFixture;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.exception.PersonNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoRootConfig.class })
public class TestPersonDaoImpl {

	@Autowired private PersonDaoImpl personDao;

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
