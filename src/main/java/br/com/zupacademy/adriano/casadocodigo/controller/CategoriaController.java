package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.CategoriaForm;
import br.com.zupacademy.adriano.casadocodigo.model.Categoria;
import br.com.zupacademy.adriano.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.adriano.casadocodigo.validator.NomeCategoriaDuplicadoValidator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    private final NomeCategoriaDuplicadoValidator nomeCategoriaDuplicado;

    public CategoriaController(CategoriaRepository categoriaRepository, NomeCategoriaDuplicadoValidator nomeCategoriaDuplicado) {
        this.categoriaRepository = categoriaRepository;
        this.nomeCategoriaDuplicado = nomeCategoriaDuplicado;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CategoriaForm categoriaForm) throws Exception {
        Categoria categoria = categoriaForm.toModel();
        categoriaRepository.save(categoria);
    }
}
