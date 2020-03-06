package com.softplan.cadastro.pessoa.model.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.softplan.cadastro.pessoa.validator.PessoaExistenteComJPAValidator;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PessoaExistenteComJPAValidator.class)
public @interface PessoaExistenteValidator {

	String message() default "Registro não encontrado";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
