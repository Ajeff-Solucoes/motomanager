package br.com.jeffersondev.motormanager.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ApiError {

	private int code;
	private HttpStatus status;
	private String message;
	private Date timestamp;

	public ApiError(HttpStatus status, int code, String message, Date timestamp) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.timestamp = timestamp;
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

}
