package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.dto.ClienteDto;
import br.com.zupacademy.adriano.casadocodigo.controller.form.ClienteForm;
import br.com.zupacademy.adriano.casadocodigo.model.Cliente;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final EntityManager entityManager;

    public ClienteController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping
    @Transactional
    public ClienteDto cadastar(@RequestBody @Valid ClienteForm clienteForm) {
        Cliente cliente = clienteForm.toModel(entityManager);
        entityManager.persist(cliente);
        return new ClienteDto(cliente.getId());
    }
}
