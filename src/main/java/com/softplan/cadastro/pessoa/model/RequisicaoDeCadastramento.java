package com.softplan.cadastro.pessoa.model;

import java.time.LocalDate;
import java.util.Optional;

public interface RequisicaoDeCadastramento {

	String getNome();
	
	Optional<Sexo> getSexo();
	
	LocalDate getDataDeNascimento();
	
	Optional<String> getNaturalidade();
	
	Optional<String> getNacionalidade();
	
	Optional<String> getEmail();
	
	String getCpf();
}
