package com.softplan.cadastro.pessoa.rest;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softplan.cadastro.pessoa.modelo.ErroDeValidacao;
import com.softplan.cadastro.pessoa.modelo.ErroDeValidacaoException;
import com.softplan.cadastro.pessoa.modelo.Pessoa;
import com.softplan.cadastro.pessoa.modelo.PessoaRepository;
import com.softplan.cadastro.pessoa.modelo.RequisicaoDeAtualizacao;
import com.softplan.cadastro.pessoa.modelo.RequisicaoDeCadastramento;
import com.softplan.cadastro.pessoa.modelo.RequisicaoDeRemocao;
import com.softplan.cadastro.pessoa.modelo.ValidadorDeCadastroDePessoa;

@Component
public class ValidadorDePessoaComBeanValidator implements ValidadorDeCadastroDePessoa {

	@Autowired
	private Validator validator;
	
	@Autowired
	private PessoaRepository repository;
	
	@Override
	public void valida(RequisicaoDeCadastramento cadastro) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeCadastramento>> erros = validator.validate(cadastro);
		Set<ErroDeValidacao> errosDeValidacao = trataConstraints(erros.stream().collect(Collectors.toSet()));
		
		if(!errosDeValidacao.isEmpty()) {
			throw new ErroDeValidacaoException(errosDeValidacao);
		}
	}
	
	@Override
	public void valida(RequisicaoDeAtualizacao atualizacao) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeAtualizacao>> erros = validator.validate(atualizacao);
		Set<ErroDeValidacao> errosDeValidacao = trataConstraints(erros.stream().collect(Collectors.toSet()));
		
		Optional<Pessoa> pessoaConsulta = repository.buscaPorId(atualizacao.getId());
		if(!pessoaConsulta.isPresent()) {
			errosDeValidacao.add(new ErroDeValidacao("generico", "Cadastro não encontrado"));
		}
		
		if(!errosDeValidacao.isEmpty()) {
			throw new ErroDeValidacaoException(errosDeValidacao);
		}
	}
	
	@Override
	public void valida(RequisicaoDeRemocao remocao) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeRemocao>> erros = validator.validate(remocao);
		Set<ErroDeValidacao> errosDeValidacao = trataConstraints(erros.stream().collect(Collectors.toSet()));
		
		Optional<Pessoa> pessoaConsulta = repository.buscaPorId(remocao.getId());
		if(!pessoaConsulta.isPresent()) {
			errosDeValidacao.add(new ErroDeValidacao("generico", "Cadastro não encontrado"));
		}
		
		if(!errosDeValidacao.isEmpty()) {
			throw new ErroDeValidacaoException(errosDeValidacao);
		}
	}
	

	private Set<ErroDeValidacao> trataConstraints(Set<ConstraintViolation> erros) {
		Set<ErroDeValidacao> errosDeValidacao = new HashSet<>();
		if(!erros.isEmpty()) {
			errosDeValidacao = erros.stream()
				.map(erro -> new ErroDeValidacao(erro.getPropertyPath().toString(), erro.getMessage()))
				.collect(Collectors.toSet());
		}
		return errosDeValidacao;
	}

}
