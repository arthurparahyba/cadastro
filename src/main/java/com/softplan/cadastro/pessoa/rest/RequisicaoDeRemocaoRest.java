package com.softplan.cadastro.pessoa.rest;

import javax.validation.constraints.NotNull;

import com.softplan.cadastro.pessoa.modelo.RequisicaoDeRemocao;

import lombok.Getter;

@Getter
public class RequisicaoDeRemocaoRest implements RequisicaoDeRemocao {

	@NotNull
	private final Long id;
	
	public RequisicaoDeRemocaoRest(Long id) {
		this.id = id;
	}

}
