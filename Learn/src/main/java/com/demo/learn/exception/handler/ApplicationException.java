package com.demo.learn.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApplicationException extends RuntimeException {

	HttpStatus httpStatus;
	String []errors;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String msg) {
		super(msg);

	}
	public ApplicationException(HttpStatus httpStatus, String...msg) {
		this.httpStatus =httpStatus;
		this.errors =msg;
	}
}
