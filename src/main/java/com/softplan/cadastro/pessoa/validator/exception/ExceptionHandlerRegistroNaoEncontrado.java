package com.softplan.cadastro.pessoa.validator.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerRegistroNaoEncontrado extends ResponseEntityExceptionHandler {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	protected ResponseEntity<Object> handleConflict(RegistroNaoEncontradoException ex, WebRequest request) {
		return handleExceptionInternal(ex, null, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
}
