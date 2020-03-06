package com.softplan.cadastro.pessoa.validator.exception;

import java.util.HashSet;
import java.util.Set;

import com.softplan.cadastro.pessoa.model.DetalheDoErro;
import com.softplan.cadastro.pessoa.model.LogicaDeNegocioException;

public class ErroDeValidacaoException extends LogicaDeNegocioException {

	private Set<DetalheDoErro> erros = new HashSet<>();
	
	public ErroDeValidacaoException() {
	}

	public ErroDeValidacaoException(DetalheDoErro erro) {
		erros.add(erro);
	}
	
	public ErroDeValidacaoException(Set<DetalheDoErro> erros) {
		this.erros = erros;
	}

	public Set<DetalheDoErro> getErros() {
		return erros;
	}

	public void setErros(Set<DetalheDoErro> erros) {
		this.erros = erros;
	}

}
