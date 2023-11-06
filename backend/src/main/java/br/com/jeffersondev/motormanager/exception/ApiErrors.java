package br.com.jeffersondev.motormanager.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiErrors {

	private int code;
	private HttpStatus status;
	private String message;
	private Date timestamp;
	private List<String> errors = new ArrayList<>();

	public ApiErrors(int code, HttpStatus status, String message, Date timestamp, List<String> errors) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
		this.errors = errors;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
