package com.trey.controller.fixture;

import com.trey.addrbook.domain.Person;

public class PersonTestFixture {

	public Person createPerson() {
		Person person = new Person();
		person.setId(1);
		person.setUsername("thoward333");
		person.setFirstName("Trey");
		person.setLastName("Howard");
		return person;
	}

}
