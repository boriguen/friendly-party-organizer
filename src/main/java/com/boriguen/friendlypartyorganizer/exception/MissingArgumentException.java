package com.boriguen.friendlypartyorganizer.exception;

public class MissingArgumentException extends Exception {
	/**
	 * Contains the serial version ID.
	 */
	private static final long serialVersionUID = -7847221165168427766L;

	public MissingArgumentException(String message) {
		super(message);
	}
}
