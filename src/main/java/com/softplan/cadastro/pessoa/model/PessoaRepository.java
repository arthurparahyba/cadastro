package com.softplan.cadastro.pessoa.model;

import java.util.List;
import java.util.Optional;

import com.softplan.cadastro.pessoa.validator.exception.ErroDeValidacaoException;

public interface PessoaRepository {

	Pessoa salva(Pessoa pessoa) throws ErroDeValidacaoException;
	
	Optional<Pessoa> buscaPorId(Long id);

	List<Pessoa> lista();

	void remove(Long id);

}
