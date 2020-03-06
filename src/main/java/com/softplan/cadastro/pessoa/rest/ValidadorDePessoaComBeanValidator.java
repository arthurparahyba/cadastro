package com.softplan.cadastro.pessoa.rest;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softplan.cadastro.pessoa.model.ErroDeValidacao;
import com.softplan.cadastro.pessoa.model.ErroDeValidacaoException;
import com.softplan.cadastro.pessoa.model.RequisicaoDeAtualizacao;
import com.softplan.cadastro.pessoa.model.RequisicaoDeCadastramento;
import com.softplan.cadastro.pessoa.model.RequisicaoDeRemocao;
import com.softplan.cadastro.pessoa.model.ValidadorDeCadastroDePessoa;

@Component
public class ValidadorDePessoaComBeanValidator implements ValidadorDeCadastroDePessoa {

	@Autowired
	private Validator validator;
	
	@Override
	public void valida(RequisicaoDeCadastramento cadastro) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeCadastramento>> erros = validator.validate(cadastro);
		trataConstraints(erros.stream().collect(Collectors.toSet()));
	}
	
	@Override
	public void valida(RequisicaoDeAtualizacao atualizacao) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeAtualizacao>> erros = validator.validate(atualizacao);
		trataConstraints(erros.stream().collect(Collectors.toSet()));
	}
	
	@Override
	public void valida(RequisicaoDeRemocao remocao) throws ErroDeValidacaoException {
		Set<ConstraintViolation<RequisicaoDeRemocao>> erros = validator.validate(remocao);
		trataConstraints(erros.stream().collect(Collectors.toSet()));
	}
	
	private void trataConstraints(Set<ConstraintViolation> constrains) throws ErroDeValidacaoException {
		Set<ErroDeValidacao> errosDeValidacao = toErrosDeValidacao(constrains);

		if(!errosDeValidacao.isEmpty()) {
			throw new ErroDeValidacaoException(errosDeValidacao);
		}
	}

	private Set<ErroDeValidacao> toErrosDeValidacao(Set<ConstraintViolation> erros) {
		Set<ErroDeValidacao> errosDeValidacao = new HashSet<>();
		if(!erros.isEmpty()) {
			errosDeValidacao = erros.stream()
				.map(erro -> new ErroDeValidacao(erro.getPropertyPath().toString(), erro.getMessage()))
				.collect(Collectors.toSet());
		}
		return errosDeValidacao;
	}

}
