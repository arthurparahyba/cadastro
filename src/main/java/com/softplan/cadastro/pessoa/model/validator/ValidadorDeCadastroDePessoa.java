package com.softplan.cadastro.pessoa.model.validator;

import com.softplan.cadastro.pessoa.model.RequisicaoDeAtualizacao;
import com.softplan.cadastro.pessoa.model.RequisicaoDeCadastramento;
import com.softplan.cadastro.pessoa.model.RequisicaoPorId;

public interface ValidadorDeCadastroDePessoa {

	void valida(RequisicaoDeCadastramento pessoa);

	void valida(RequisicaoDeAtualizacao atualizacao);

	void valida(RequisicaoPorId remove);

}