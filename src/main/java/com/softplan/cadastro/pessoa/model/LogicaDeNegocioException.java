package com.softplan.cadastro.pessoa.model;

import java.util.HashSet;
import java.util.Set;

public class LogicaDeNegocioException extends RuntimeException {

	private Set<DetalheDoErro> erros = new HashSet<>();
	
	public LogicaDeNegocioException() {
	}

	public LogicaDeNegocioException(DetalheDoErro erro) {
		erros.add(erro);
	}
	
	public LogicaDeNegocioException(Set<DetalheDoErro> erros) {
		this.erros = erros;
	}

	public Set<DetalheDoErro> getErros() {
		return erros;
	}

	public void setErros(Set<DetalheDoErro> erros) {
		this.erros = erros;
	}

}
