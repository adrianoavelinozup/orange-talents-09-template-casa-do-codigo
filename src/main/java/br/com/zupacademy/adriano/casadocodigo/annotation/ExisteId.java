package br.com.zupacademy.adriano.casadocodigo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {
    String message() default "Não encontrado";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String nomeDoCampo();

    Class<?> classeDaEntidade();
}
