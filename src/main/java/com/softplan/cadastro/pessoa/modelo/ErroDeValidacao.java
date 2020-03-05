package com.softplan.cadastro.pessoa.modelo;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class ErroDeValidacao {
	private final String atributo;
	private final String causa;
}
