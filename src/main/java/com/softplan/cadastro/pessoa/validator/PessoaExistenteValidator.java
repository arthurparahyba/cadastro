package com.softplan.cadastro.pessoa.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PessoaExistenteComJPAValidator.class)
public @interface PessoaExistenteValidator {

	String message() default "Registro não encontrado";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
