package com.softplan.cadastro.pessoa.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class ErroDeValidacaoException extends Exception {

	private Set<ErroDeValidacao> erros = new HashSet<>();

	public ErroDeValidacaoException(ErroDeValidacao erro) {
		erros.add(erro);
	}
	
	public ErroDeValidacaoException(Set<ErroDeValidacao> erros) {
		this.erros = erros;
	}

	public Set<ErroDeValidacao> getErros() {
		return erros;
	}

	public void setErros(Set<ErroDeValidacao> erros) {
		this.erros = erros;
	}

}
