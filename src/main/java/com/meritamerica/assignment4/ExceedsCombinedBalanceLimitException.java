package com.meritamerica.assignment4;

public class ExceedsCombinedBalanceLimitException extends Exception {
	
	public ExceedsCombinedBalanceLimitException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
