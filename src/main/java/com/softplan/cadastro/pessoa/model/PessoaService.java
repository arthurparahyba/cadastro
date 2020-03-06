package com.softplan.cadastro.pessoa.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softplan.cadastro.pessoa.model.Pessoa.PessoaBuilder;

@Service
public class PessoaService {
	
	@Autowired
	private ValidadorDeCadastroDePessoa validador;

	@Autowired
	private PessoaRepository repository;

	public List<Pessoa> lista() {
		return repository.lista();
	}
	
	public Pessoa cadastra(RequisicaoDeCadastramento requisicao){
		validador.valida(requisicao);
		Pessoa.PessoaBuilder builder = preencheDadosDePessoa(requisicao);
		Pessoa pessoa = builder.build();
		
		return repository.salva(pessoa);
	}
	
	public Pessoa busca(RequisicaoPorId requisicao){
		validador.valida(requisicao);
		return repository.buscaPorId(requisicao.getId()).get();
	}
	
	public void remove(RequisicaoPorId requisicao){
		validador.valida(requisicao);
		repository.remove(requisicao.getId());
	}
	
	public Pessoa atualiza(RequisicaoDeAtualizacao atualizacao){
		validador.valida(atualizacao);
		
		Pessoa existente = repository.buscaPorId(atualizacao.getId()).get();
		
		PessoaBuilder pessoaBuilder = this.preencheDadosDePessoa(atualizacao);
		pessoaBuilder.id(existente.getId());
		pessoaBuilder.dataDeCriacao(existente.getDataDeCriacao());
		
		Pessoa atualizada = pessoaBuilder.build();
		return repository.salva(atualizada);
	}

	private Pessoa.PessoaBuilder preencheDadosDePessoa(RequisicaoDeCadastramento novo) {
		Pessoa.PessoaBuilder builder = Pessoa.builder();
		builder
			.nome(novo.getNome())
			.dataDeNascimento(novo.getDataDeNascimento())
			.cpf(novo.getCpf());
		
		if(novo.getNacionalidade().isPresent()) {
			builder = builder.nacionalidade(novo.getNacionalidade().get());
		}
		
		if(novo.getNaturalidade().isPresent()) {
			builder = builder.naturalidade(novo.getNaturalidade().get());
		}
		
		if(novo.getEmail().isPresent()) {
			builder = builder.email(novo.getEmail().get());
		}
		
		if(novo.getSexo().isPresent()) {
			builder = builder.sexo(novo.getSexo().get());
		}
		return builder;
	}

}
