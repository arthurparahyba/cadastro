package com.softplan.cadastro.pessoa.modelo;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {

	Pessoa salva(Pessoa pessoa) throws ErroDeValidacaoException;
	
	Optional<Pessoa> buscaPorId(Long id);

	List<Pessoa> lista();

	void remove(Long id);

}
