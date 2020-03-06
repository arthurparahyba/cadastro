package com.softplan.cadastro.pessoa.model;

public interface ValidadorDeCadastroDePessoa {

	void valida(RequisicaoDeCadastramento pessoa) throws LogicaDeNegocioException;

	void valida(RequisicaoDeAtualizacao atualizacao) throws LogicaDeNegocioException;

	void valida(RequisicaoPorId remove) throws LogicaDeNegocioException;

}