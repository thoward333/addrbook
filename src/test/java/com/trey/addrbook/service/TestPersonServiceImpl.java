package com.trey.addrbook.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.trey.addrbook.dao.PersonDao;
import com.trey.addrbook.domain.Person;
import com.trey.addrbook.service.fixture.ServiceTestFixture;
import com.trey.addrbook.springconfig.ServiceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ServiceTestConfig.class })
public class TestPersonServiceImpl {

	@Autowired private PersonService personService;
	@Autowired private PersonDao mockPersonDao;

	@Test
	public void test_getPersonById() {
		ServiceTestFixture f = new ServiceTestFixture();
		Person personExists = f.createTrey();

		when(mockPersonDao.findById(anyInt())).thenReturn(personExists);

		Person personFound = personService.getPersonById(personExists.getId());

		assertEquals(personExists.getId(), personFound.getId());
		assertEquals(personExists, personFound);
	}

	@Test
	public void test_savePerson() {
		ServiceTestFixture f = new ServiceTestFixture();
		Person personExists = f.createTrey();
		personExists.setId(null);

		final Holder invokeCounter = new Holder(); // since we can't use verify() on void method
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) throws Throwable {
				invokeCounter.value = invokeCounter.value + 1;
				return null;
			}
		}).when(mockPersonDao).insert((Person) anyObject());

		personService.savePerson(personExists);

		assertEquals(1, invokeCounter.value);
	}

	private class Holder {
		public int value = 0;
	}

}
