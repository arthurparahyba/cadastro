package com.softplan.cadastro.pessoa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.cadastro.pessoa.modelo.ErroDeValidacaoException;
import com.softplan.cadastro.pessoa.modelo.Pessoa;
import com.softplan.cadastro.pessoa.modelo.PessoaService;
import com.softplan.cadastro.pessoa.modelo.RequisicaoDeRemocao;

@RestController
@RequestMapping(path="/rest/pessoa")
public class PessoaRest {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity lista() {
		return ResponseEntity.ok(pessoaService.lista());
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity remove(@PathVariable("id") Long id) throws ErroDeValidacaoException {
		RequisicaoDeRemocao requisicao = new RequisicaoDeRemocaoRest(id);
		pessoaService.remove(requisicao);
		return ResponseEntity.accepted().build();
	}

	@PostMapping
	public ResponseEntity<Pessoa> cadastra(@RequestBody RequisicaoDeCadastramentoComRest cadastro) throws ErroDeValidacaoException {
		Pessoa pessoa = pessoaService.cadastra(cadastro);
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping
	public ResponseEntity<Pessoa> atualiza(@RequestBody RequisicaoDeAtualizacaoComRest atualizacao) throws ErroDeValidacaoException {
		Pessoa pessoa = pessoaService.atualiza(atualizacao);
		return ResponseEntity.ok(pessoa);
	}
}
