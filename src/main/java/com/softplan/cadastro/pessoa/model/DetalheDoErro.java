package com.softplan.cadastro.pessoa.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class DetalheDoErro {
	private final String atributo;
	private final String causa;
}
