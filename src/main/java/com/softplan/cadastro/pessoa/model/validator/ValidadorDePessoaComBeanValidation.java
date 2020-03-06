package com.softplan.cadastro.pessoa.model.validator;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.softplan.cadastro.pessoa.model.DetalheDoErro;
import com.softplan.cadastro.pessoa.model.RequisicaoDeAtualizacao;
import com.softplan.cadastro.pessoa.model.RequisicaoDeCadastramento;
import com.softplan.cadastro.pessoa.model.RequisicaoPorId;
import com.softplan.cadastro.pessoa.model.exception.ErroDeValidacaoException;
import com.softplan.cadastro.pessoa.model.exception.RegistroNaoEncontradoException;

@Component
public class ValidadorDePessoaComBeanValidation implements ValidadorDeCadastroDePessoa {

	@Autowired
	private Validator validator;
	
	@Override
	public void valida(RequisicaoDeCadastramento cadastro) {
		Set<ConstraintViolation<RequisicaoDeCadastramento>> erros = validator.validate(cadastro);
		trataConstraints(erros.stream().collect(Collectors.toSet()));
	}
	
	@Override
	public void valida(RequisicaoDeAtualizacao atualizacao) {
		Set<ConstraintViolation<RequisicaoDeAtualizacao>> erros = validator.validate(atualizacao);
		trataConstraints(erros.stream().collect(Collectors.toSet()));
	}
	
	@Override
	public void valida(RequisicaoPorId requisicao) {
		Set<ConstraintViolation<RequisicaoPorId>> erros = validator.validate(requisicao);
		if(!erros.isEmpty()) {
			throw new RegistroNaoEncontradoException();
		}
	}
	
	private void trataConstraints(Set<ConstraintViolation> constrains) throws ErroDeValidacaoException {
		Set<DetalheDoErro> errosDeValidacao = toErrosDeValidacao(constrains);

		if(!errosDeValidacao.isEmpty()) {
			throw new ErroDeValidacaoException(errosDeValidacao);
		}
	}

	private Set<DetalheDoErro> toErrosDeValidacao(Set<ConstraintViolation> erros) {
		Set<DetalheDoErro> errosDeValidacao = new HashSet<>();
		if(!erros.isEmpty()) {
			errosDeValidacao = erros.stream()
				.map(erro -> new DetalheDoErro(erro.getPropertyPath().toString(), erro.getMessage()))
				.collect(Collectors.toSet());
		}
		return errosDeValidacao;
	}

}
