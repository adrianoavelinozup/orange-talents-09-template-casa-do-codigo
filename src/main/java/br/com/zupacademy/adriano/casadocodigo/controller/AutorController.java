package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.AutorForm;
import br.com.zupacademy.adriano.casadocodigo.model.Autor;
import br.com.zupacademy.adriano.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.adriano.casadocodigo.validator.EmailDuplicadoValidator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final EmailDuplicadoValidator emailDuplicadoValidator;

    private final AutorRepository autorRepository;

    public AutorController(EmailDuplicadoValidator emailDuplicadoValidator, AutorRepository autorRepository) {
        this.emailDuplicadoValidator = emailDuplicadoValidator;
        this.autorRepository = autorRepository;
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(emailDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public void salvar(@RequestBody @Valid  AutorForm autorForm) {
        Autor autor = autorForm.toModel();
        autorRepository.save(autor);
   }
   
}
