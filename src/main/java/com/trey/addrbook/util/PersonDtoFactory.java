package com.trey.addrbook.util;

import org.springframework.stereotype.Component;

import com.trey.addrbook.domain.Person;
import com.trey.addrbook.dto.PersonDto;

@Component
public class PersonDtoFactory {

	public PersonDto createFromDomain(Person domain) {
		// TODO convert to dozer
		PersonDto dto = new PersonDto();
		dto.setId(domain.getId());
		dto.setFullname(domain.getFirstName() + " " + domain.getLastName());
		return dto;
	}

}
