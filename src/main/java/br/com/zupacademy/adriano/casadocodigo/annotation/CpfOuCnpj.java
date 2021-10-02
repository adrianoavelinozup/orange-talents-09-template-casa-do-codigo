package br.com.zupacademy.adriano.casadocodigo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {CpfOuCnpjValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfOuCnpj {
    String message() default "Documento inválido. Ex: 999.999.999-99 para CPF ou 99.999.999/9999-99 para CNPJ";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String nomeDoCampo();

    Class<?> classeDaEntidade();
}
