package com.trey.addrbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trey.addrbook.dao.PersonDao;
import com.trey.addrbook.domain.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonDao personDao;

	@Autowired
	public PersonServiceImpl(PersonDao personDao) {
		super();
		this.personDao = personDao;
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.findById(id);
	}

	@Override
	public void savePerson(Person person) {
		personDao.insert(person);
	}

//	@Override
//	public void updatePerson(Person p) {
//		personDao.update(p);
//	}

}
