package com.softplan.cadastro.pessoa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.softplan.cadastro.pessoa.modelo.Pessoa;

@Repository
public interface PessoaDAO extends CrudRepository<Pessoa, Long> {

	
}
