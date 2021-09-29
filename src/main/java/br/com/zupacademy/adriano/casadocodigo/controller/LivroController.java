package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.form.LivroForm;
import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import br.com.zupacademy.adriano.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.adriano.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.adriano.casadocodigo.repository.LivroRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final AutorRepository autorRepository;
    private final CategoriaRepository categoriaRepository;
    private final LivroRepository livroRepository;

    public LivroController(AutorRepository autorRepository, CategoriaRepository categoriaRepository, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.categoriaRepository = categoriaRepository;
        this.livroRepository = livroRepository;
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid LivroForm livroForm) {
        Livro livro = livroForm.toMap(autorRepository, categoriaRepository);
        livroRepository.save(livro);
    }
}
