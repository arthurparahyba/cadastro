package com.softplan.cadastro.pessoa.rest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.softplan.cadastro.pessoa.model.ErroDeValidacaoException;

@ControllerAdvice
public class ErroDeValidacaoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ErroDeValidacaoException.class)
	protected ResponseEntity<Object> handleConflict(ErroDeValidacaoException ex, WebRequest request) {
		Map<String, String> erros = new HashMap<>();
		
		ex.getErros().forEach(erro -> {
			erros.put(erro.getAtributo(), erro.getCausa());
		});
		
		return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
