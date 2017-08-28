package com.rnr.letsbuy.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 257310783489774609L;

	public UserNotFoundException() {
		super("User not found");
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
