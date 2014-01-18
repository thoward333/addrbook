package com.trey.addrbook.dao;

import com.trey.addrbook.domain.Person;

public interface PersonDao {

	Person findById(Integer id);

	void insert(Person person);

	void update(Person p);

}