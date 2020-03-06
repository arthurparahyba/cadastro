package com.softplan.cadastro.pessoa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@AllArgsConstructor
@Getter
@Table(uniqueConstraints=@UniqueConstraint(name=Pessoa.CPF_UNICO, columnNames="cpf"))
public final class Pessoa {
	
	public static final String CPF_UNICO = "CPF_UNICO";
	
	@PrePersist
	private void prePersist() {
		this.dataDeCriacao = LocalDateTime.now();
		this.ultimaAtualizacao = LocalDateTime.now();
	}
	
	@PreUpdate
	private void preUpdate() {
		this.ultimaAtualizacao = LocalDateTime.now();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;
	
	@Email
	private String email;
	
	@Column(nullable=false)
	private LocalDate dataDeNascimento;
	
	private String naturalidade;
	
	private String nacionalidade;
	
	@Column(nullable=false)
	private String cpf;
	
	private LocalDateTime dataDeCriacao;
	
	private LocalDateTime ultimaAtualizacao;
	
	public Pessoa() {
	}

	public Pessoa(String nome, LocalDate dataDeNascimento, String cpf) {
		this.nome = nome;
		this.dataDeNascimento = dataDeNascimento;
		this.cpf = cpf;
	}
	
}
