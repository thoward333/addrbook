package com.trey.addrbook.service;

import com.trey.addrbook.domain.Person;

public interface PersonService {

	public Person getPersonById(Integer id);

	public void savePerson(Person person);
	
	public void updatePerson(Person person);

}
