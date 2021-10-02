package br.com.zupacademy.adriano.casadocodigo.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {EstadoValidoValidator.class})
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EstadoValido {
    String message() default "selecione um Estado válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
