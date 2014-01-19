package com.trey.addrbook.exception;

/**
 * Thrown when performing an operatoin on a user that does not exist.
 * 
 * @author Trey
 */
public class PersonNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException() {
		super();
	}

	public PersonNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PersonNotFoundException(String message) {
		super(message);
	}

	public PersonNotFoundException(Throwable cause) {
		super(cause);
	}

}
