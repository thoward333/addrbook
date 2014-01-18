package com.trey.addrbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trey.addrbook.domain.Person;
import com.trey.addrbook.dto.PersonDto;
import com.trey.addrbook.dto.save.SavePersonRequest;
import com.trey.addrbook.service.PersonService;

/**
 * @author Adapted from http://codetutr.com/2013/04/09/spring-mvc-easy-rest-based-json-services-with-responsebody/
 */
@Controller
public class PersonController {

	private PersonService personService;

	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping("person/{id}")
	@ResponseBody
	public PersonDto getById(@PathVariable Integer id) {
		return convertToDto(personService.getPersonById(id));
	}

	// same as above method, just showing different URL mapping
	@RequestMapping(value = "person", params = "id")
	@ResponseBody
	public PersonDto getByIdFromParam(@RequestParam Integer id) {
		return convertToDto(personService.getPersonById(id));
	}

	// handles person form submit
	@RequestMapping(value = "person", method = RequestMethod.POST)
	@ResponseBody
	public Integer createPerson(@RequestBody SavePersonRequest request) {
		Person person = new Person();
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		person.setUsername(request.getUsername());
		personService.savePerson(person);
		return person.getId();
	}

	private static PersonDto convertToDto(Person domain) {
		// TODO convert to dozer
		PersonDto dto = new PersonDto();
		dto.setId(domain.getId());
		dto.setFullname(domain.getFirstName() + " " + domain.getLastName());
		return dto;
	}

}
