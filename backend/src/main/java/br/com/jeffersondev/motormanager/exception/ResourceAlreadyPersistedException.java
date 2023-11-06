package br.com.jeffersondev.motormanager.exception;

public class ResourceAlreadyPersistedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceAlreadyPersistedException(String msg) {
		super(msg);
	}

}
