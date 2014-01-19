package com.trey.addrbook.dao.fixture;

import com.trey.addrbook.domain.Person;

/**
 * Test fixture for unit tests.
 * 
 * @author Trey
 */
public class DaoImplTestFixture {

	/**
	 * Creates a Person with id=1, username=thoward333, firstName=Trey, lastName=Howard
	 * @return
	 */
	public Person createTrey() {
		Person person = new Person();
		person.setId(1);
		person.setUsername("thoward333");
		person.setFirstName("Trey");
		person.setLastName("Howard");
		return person;
	}

}
