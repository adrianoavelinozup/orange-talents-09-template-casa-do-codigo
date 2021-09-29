package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.LivroForm;
import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid LivroForm livroForm) {
        Livro livro = livroForm.toMap(entityManager);
        entityManager.persist(livro);
    }
}
