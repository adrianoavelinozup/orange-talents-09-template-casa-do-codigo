package br.com.zupacademy.adriano.casadocodigo.validator;

import br.com.zupacademy.adriano.casadocodigo.controller.form.CategoriaForm;
import br.com.zupacademy.adriano.casadocodigo.model.Categoria;
import br.com.zupacademy.adriano.casadocodigo.repository.CategoriaRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class NomeCategoriaDuplicadoValidator implements Validator {
    private final CategoriaRepository categoriaRepository;

    public NomeCategoriaDuplicadoValidator(CategoriaRepository autorRepository) {
        this.categoriaRepository = autorRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CategoriaForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CategoriaForm categoriaForm = (CategoriaForm) target;
        Optional<Categoria> categoriaEncontrada = categoriaRepository.findByNome(categoriaForm.getNome());

        if (categoriaEncontrada.isPresent()) {
            errors.rejectValue("nome", null,
                    "A categoria " + categoriaForm.getNome() + " já está cadastrada. Adicione uma nova categoria");
        }
    }
}
