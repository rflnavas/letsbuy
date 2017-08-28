package com.rnr.letsbuy.exception;

public class NoItemFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 272261987195265816L;

	public NoItemFoundException() {
		super("No item was found");
	}
	
	public NoItemFoundException(String msg){
		super(msg);
	}
}
