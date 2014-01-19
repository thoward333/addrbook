package com.trey.addrbook.service;

import com.trey.addrbook.domain.Person;

/**
 * Service for managing people.
 * 
 * @author Trey
 */
public interface PersonService {

	/**
	 * @param id
	 * @return Returns the person with the given id.
	 */
	public Person getPersonById(Integer id);

	/**
	 * Creates a new Person and populates the <code>id</code> property with the generated id.
	 * @param person All non-id properties are used to create the new person.
	 */
	public void savePerson(Person person);
	
//	/**
//	 * @param person The person to be updated.
//	 */
//	public void updatePerson(Person person);

}
