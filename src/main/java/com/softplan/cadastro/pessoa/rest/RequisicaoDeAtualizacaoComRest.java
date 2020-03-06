package com.softplan.cadastro.pessoa.rest;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.softplan.cadastro.pessoa.model.RequisicaoDeAtualizacao;
import com.softplan.cadastro.pessoa.model.Sexo;
import com.softplan.cadastro.pessoa.validator.PessoaExistenteValidator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoDeAtualizacaoComRest implements RequisicaoDeAtualizacao{

	@NotNull
	@PessoaExistenteValidator
	private Long id;
	
	@NotEmpty
	private String nome;
	
	private Sexo sexo;
	
	@NotNull
	@PastOrPresent
	private LocalDate dataDeNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	@Email
	private String email;
	
	@NotEmpty
	@CPF
	private String cpf;

	public Optional<Sexo> getSexo() {
		return Optional.ofNullable(sexo);
	}

	public Optional<String> getNaturalidade() {
		return Optional.ofNullable(naturalidade);
	}

	public Optional<String> getNacionalidade() {
		return Optional.ofNullable(nacionalidade);
	}

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}
}
