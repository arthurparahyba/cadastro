package com.softplan.cadastro.pessoa.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.softplan.cadastro.pessoa.model.validator.PessoaExistenteValidator;
import com.softplan.cadastro.pessoa.repository.PessoaDAO;

public class PessoaExistenteComJPAValidator implements ConstraintValidator<PessoaExistenteValidator, Long>{

	private PessoaDAO pessoaDAO;
	
	public PessoaExistenteComJPAValidator(PessoaDAO pessoaDAO) {
		this.pessoaDAO = pessoaDAO;
	}

	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		return value!= null && pessoaDAO.findById(value).isPresent();
	}

}
