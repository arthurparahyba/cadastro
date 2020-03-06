package com.softplan.cadastro.pessoa.repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import com.softplan.cadastro.pessoa.model.DetalheDoErro;
import com.softplan.cadastro.pessoa.model.Pessoa;
import com.softplan.cadastro.pessoa.model.PessoaRepository;
import com.softplan.cadastro.pessoa.model.exception.ErroDeValidacaoException;

@Repository
public class PessoaRepositoryComSpringData implements PessoaRepository {

	@Autowired
	private PessoaDAO dao;
	
	@Override
	public List<Pessoa> lista() {
		List<Pessoa> pessoasList = new ArrayList<>();
		Iterable<Pessoa> pessoas = dao.findAll();
		for(Pessoa pessoa : pessoas) {
			pessoasList.add(pessoa);
		}
		return pessoasList;
	}

	@Override
	public Pessoa salva(Pessoa pessoa) throws ErroDeValidacaoException {
		try {
			return dao.save(pessoa);
		} catch (DataIntegrityViolationException e) {
			if(erroDeCPFUnico(e)) {
				DetalheDoErro erro = new DetalheDoErro("cpf", "Já existe um cadastro associado com este CPF");
				throw new ErroDeValidacaoException(erro);
			}
			DetalheDoErro erro = new DetalheDoErro("generico", "Erro no cadastramento");
			throw new ErroDeValidacaoException(erro);
		}
	}
	
	@Override
	public Optional<Pessoa> buscaPorId(Long id) {
		return dao.findById(id);
	}
	
	private boolean erroDeCPFUnico(DataIntegrityViolationException e) {
		if(!(e.getCause() instanceof ConstraintViolationException)) {
			return false;
		}
		
		ConstraintViolationException constraint = (ConstraintViolationException)e.getCause();
		return constraint.getConstraintName().contains(Pessoa.CPF_UNICO);
	}

	@Override
	public void remove(Long id) {
		dao.deleteById(id);
	}

}
