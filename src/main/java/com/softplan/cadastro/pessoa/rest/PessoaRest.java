package com.softplan.cadastro.pessoa.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softplan.cadastro.pessoa.model.LogicaDeNegocioException;
import com.softplan.cadastro.pessoa.model.Pessoa;
import com.softplan.cadastro.pessoa.model.PessoaService;
import com.softplan.cadastro.pessoa.model.RequisicaoPorId;

@RestController
@RequestMapping(path="/rest/pessoa")
public class PessoaRest {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> lista() {
		return ResponseEntity.ok(pessoaService.lista());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> getPorId(@PathVariable("id") Long id) {
		RequisicaoPorId requisicao = new RequisicaoPorIdRest(id);
		return ResponseEntity.ok(pessoaService.busca(requisicao));
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> remove(@PathVariable("id") Long id) {
		RequisicaoPorId requisicao = new RequisicaoPorIdRest(id);
		pessoaService.remove(requisicao);
		return ResponseEntity.accepted().build();
	}

	@PostMapping
	public ResponseEntity<?> cadastra(@RequestBody RequisicaoDeCadastramentoComRest cadastro)  {
		Pessoa pessoa = pessoaService.cadastra(cadastro);
		
		try {
			EntityModel<Pessoa> pessoaResource = new EntityModel<Pessoa>(pessoa, 
					linkTo(methodOn(PessoaRest.class).getPorId(pessoa.getId())).withSelfRel(),
					linkTo(methodOn(PessoaRest.class).lista()).withRel("pessoas"));
			
			return ResponseEntity
					.created(new URI(pessoaResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
					.body(pessoaResource);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body(cadastro);
		}
	}
	
	@PutMapping
	public ResponseEntity<?> atualiza(@RequestBody RequisicaoDeAtualizacaoComRest atualizacao) {
		Pessoa pessoa = pessoaService.atualiza(atualizacao);
		try {
			EntityModel<Pessoa> pessoaResource = new EntityModel<Pessoa>(pessoa, 
					linkTo(methodOn(PessoaRest.class).getPorId(pessoa.getId())).withSelfRel(),
					linkTo(methodOn(PessoaRest.class).lista()).withRel("pessoas"));
			
			return ResponseEntity.noContent()
					.location(new URI(pessoaResource.getRequiredLink(IanaLinkRelations.SELF).getHref())).build();
		}catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body(atualizacao);
		}
		
	}
}
