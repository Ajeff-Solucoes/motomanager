package br.com.jeffersondev.motormanager.exception;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptionController {

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = MissingServletRequestParameterException.class)
	public ApiError handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
		String error = "O parâmetro " + ex.getParameterName() + " deve ser informado!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), error, new Date());
	}

	
//	@ResponseStatus(HttpStatus.UNAUTHORIZED)
//	@ExceptionHandler( value = AccessDeniedException.class)
//	public ApiError handleAccessDeniedException(AccessDeniedException ex) {
//		HttpStatus status = HttpStatus.UNAUTHORIZED;
//		String error = "Acesso negado. Você não tem permissão para acessar este recurso!";
//		return new ApiError(status, status.value(), error, new Date());
//	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = NullPointerException.class)
	public ApiError handleNullPointerExceptionException(NullPointerException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), ex.getMessage(), new Date());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = DateTimeParseException.class)
	public ApiError handleDateTimeParseException(DateTimeParseException ex) {
		String error = "Erro no formato da data. Verifique e tente novamente!";

		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), error, new Date());
	}
	

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler( value = ArithmeticException.class)
	public ApiError handleArithmeticException(ArithmeticException ex) {
		String error = "Erro ao realizar o calculo. Tente novamente!";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ApiError(status, status.value(), error, new Date());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = ValorInformadoInvalidoException.class)
	public ApiError handleValorInformadoInvalidoExceptionException(ValorInformadoInvalidoException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), ex.getMessage(), new Date());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = NumberFormatException.class)
	public ApiError handleNumberFormatExceptionException(NumberFormatException ex) {
		String error = "Verifique se o valor informado é um número e se o mesmo está correto!";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), error, new Date());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = ConversionFailedException.class)
	public ApiError handleConversionFailedException(ConversionFailedException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Ocorreu um erro na operação, verifique os dados e tente novamente";
		return new ApiError(status, status.value(), error, new Date());
	}

	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler( value = ResourceNotFoundException.class)
	public ApiError handleResourceNotFoundException(ResourceNotFoundException ex) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		return new ApiError(status, status.value(), ex.getLocalizedMessage(), new Date());
	}

	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( value = ResourceAlreadyPersistedException.class)
	public ApiError handleResourceAlreadyPersistedException(ResourceAlreadyPersistedException ex) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiError(status, status.value(), ex.getLocalizedMessage(), new Date());
	}
	
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler( value = OperationNotExecutedException.class)
	public ApiError handleOperationNotExecutedException(OperationNotExecutedException ex) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		return new ApiError(status, status.value(), ex.getLocalizedMessage(), new Date());
	}

	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler( value = DataIntegrityViolationException.class)
	public ApiError handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String error = "Erro de integridade. A operação não poderá ser realizada!";
		return new ApiError(status, status.value(), error, new Date());
	}


	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler( MethodArgumentNotValidException.class)
	public ApiErrors handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<String> errors = new ArrayList<>();
		String message = "Erro de validação. Valor(es) não satisfaz(em) o(s) requisito(s) minimo(s)";
		for(FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + " : " + error.getDefaultMessage());
		}
		
		for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
		}
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		return new ApiErrors(status.value(), status, message, new Date(), errors);
	}


}
