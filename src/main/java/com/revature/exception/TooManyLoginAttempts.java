package com.revature.exception;

public class TooManyLoginAttempts extends RuntimeException{

	public TooManyLoginAttempts(String message){
		super(message);
	}
}
