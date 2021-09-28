package br.com.zupacademy.adriano.casadocodigo.validator;


import br.com.zupacademy.adriano.casadocodigo.controller.form.AutorForm;
import br.com.zupacademy.adriano.casadocodigo.model.Autor;
import br.com.zupacademy.adriano.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class EmailDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AutorForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        AutorForm autorForm = (AutorForm) target;
        Optional<Autor> autorEncontrado = autorRepository.findByEmail(autorForm.getEmail());

        if (autorEncontrado.isPresent()) {
            errors.rejectValue("email", null,
                    "O email " + autorForm.getEmail() + " já está cadastrado. Adicione um outro email");
        }
    }
}
