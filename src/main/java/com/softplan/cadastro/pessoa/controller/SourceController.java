package com.softplan.cadastro.pessoa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceController {

	@GetMapping
	public String urlGit() {
		return "https://github.com/arthurparahyba/cadastro";
	}
}
