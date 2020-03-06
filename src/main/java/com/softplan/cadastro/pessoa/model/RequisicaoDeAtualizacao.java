package com.softplan.cadastro.pessoa.model;

import javax.validation.constraints.NotNull;

import com.softplan.cadastro.pessoa.model.validator.PessoaExistenteValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoDeAtualizacao extends RequisicaoDeCadastramento{

	@NotNull
	@PessoaExistenteValidator
	private Long id;
}
