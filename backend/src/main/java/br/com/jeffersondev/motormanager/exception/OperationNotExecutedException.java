package br.com.jeffersondev.motormanager.exception;

public class OperationNotExecutedException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public OperationNotExecutedException(String msg) {
		super(msg);
	}

}
