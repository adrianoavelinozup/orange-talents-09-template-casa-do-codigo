package br.com.zupacademy.adriano.casadocodigo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {NomeDeEstadoUnicoPorPaisValidator.class})
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeDeEstadoUnicoPorPais {
    String message() default "já está cadastrado neste país. Escolha outro nome";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
