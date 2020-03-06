package com.softplan.cadastro.pessoa.model;

public interface ValidadorDeCadastroDePessoa {

	void valida(RequisicaoDeCadastramento pessoa) throws ErroDeValidacaoException;

	void valida(RequisicaoDeAtualizacao atualizacao) throws ErroDeValidacaoException;

	void valida(RequisicaoDeRemocao remove) throws ErroDeValidacaoException;

}