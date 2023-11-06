package br.com.jeffersondev.motormanager.exception;

public class ResourceShouldNotBeNullException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceShouldNotBeNullException(String msg) {
		super(msg);
	}

}
