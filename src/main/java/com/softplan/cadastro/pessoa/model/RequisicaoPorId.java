package com.softplan.cadastro.pessoa.model;

import javax.validation.constraints.NotNull;

import com.softplan.cadastro.pessoa.model.validator.PessoaExistenteValidator;

import lombok.Getter;

@Getter
public class RequisicaoPorId {

	@NotNull
	@PessoaExistenteValidator
	private final Long id;
	
	public RequisicaoPorId(Long id) {
		this.id = id;
	}

}
