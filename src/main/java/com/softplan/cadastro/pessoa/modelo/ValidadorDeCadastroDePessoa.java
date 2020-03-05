package com.softplan.cadastro.pessoa.modelo;

import com.softplan.cadastro.pessoa.rest.RequisicaoDeCadastramentoComRest;

public interface ValidadorDeCadastroDePessoa {

	void valida(RequisicaoDeCadastramento pessoa) throws ErroDeValidacaoException;

	void valida(RequisicaoDeAtualizacao atualizacao) throws ErroDeValidacaoException;

	void valida(RequisicaoDeRemocao remove) throws ErroDeValidacaoException;

}