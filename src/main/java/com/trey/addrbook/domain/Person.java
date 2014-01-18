package com.trey.addrbook.domain;

/**
 * Domain entity.
 * 
 * @author Trey
 */
public class Person {

	private Integer id;
	private String username;
	private String firstName;
	private String lastName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", username=").append(username).append(", firstName=")
				.append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}

}
