package com.softplan.cadastro.pessoa.rest;

import javax.validation.constraints.NotNull;

import com.softplan.cadastro.pessoa.model.RequisicaoPorId;
import com.softplan.cadastro.pessoa.validator.PessoaExistenteValidator;

import lombok.Getter;

@Getter
public class RequisicaoPorIdRest implements RequisicaoPorId {

	@NotNull
	@PessoaExistenteValidator
	private final Long id;
	
	public RequisicaoPorIdRest(Long id) {
		this.id = id;
	}

}
