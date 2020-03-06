package com.softplan.cadastro.pessoa.rest;

import java.time.LocalDate;
import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.hibernate.validator.constraints.br.CPF;

import com.softplan.cadastro.pessoa.model.RequisicaoDeCadastramento;
import com.softplan.cadastro.pessoa.model.Sexo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequisicaoDeCadastramentoComRest implements RequisicaoDeCadastramento{

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
