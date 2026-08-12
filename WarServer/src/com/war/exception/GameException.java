package com.war.exception;

public class GameException extends RuntimeException {

	private static final long serialVersionUID = -4897548649208070444L;

	public GameException() {

	}
	
	public GameException(String str){
		super(str);
	}
	
	public GameException(Throwable ex){
		super(ex);
	}
	
}
