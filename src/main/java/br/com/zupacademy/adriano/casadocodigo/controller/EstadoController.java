package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.EstadoForm;
import br.com.zupacademy.adriano.casadocodigo.model.Estado;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    private final EntityManager entityManager;

    public EstadoController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid EstadoForm estadoForm) {
        Estado estado = estadoForm.toModel(entityManager);
        entityManager.persist(estado);
    }
}
