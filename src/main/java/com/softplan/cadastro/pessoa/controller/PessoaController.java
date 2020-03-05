package com.softplan.cadastro.pessoa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	@GetMapping
	public String formulario() {
		return "pessoa/formulario";
	}
}
