package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.PaisForm;
import br.com.zupacademy.adriano.casadocodigo.model.Pais;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    private final EntityManager entityManager;

    public PaisController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid PaisForm paisForm) {
        Pais pais = paisForm.toModel();
        entityManager.persist(pais);
    }
}
