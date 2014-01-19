package com.trey.addrbook.domain;

/**
 * Domain entity.
 * 
 * @author Trey
 */
public class Person {

	private Integer id;
	private String userName;
	private String firstName;
	private String lastName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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
		builder.append("Person [id=").append(id).append(", userName=").append(userName).append(", firstName=")
				.append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}

}
