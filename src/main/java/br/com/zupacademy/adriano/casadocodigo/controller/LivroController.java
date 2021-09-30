package br.com.zupacademy.adriano.casadocodigo.controller;

import br.com.zupacademy.adriano.casadocodigo.controller.dto.LivroDetalheDto;
import br.com.zupacademy.adriano.casadocodigo.controller.dto.LivroDto;
import br.com.zupacademy.adriano.casadocodigo.controller.form.LivroForm;
import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import br.com.zupacademy.adriano.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid LivroForm livroForm) {
        Livro livro = livroForm.toMap(entityManager);
        entityManager.persist(livro);
    }

    @GetMapping
    public Page<LivroDto> listar(Pageable page) {
        Page<Livro> livros = livroRepository.findAll(page);
        return livros.map(livro -> {
            return new LivroDto(livro.getId(), livro.getTitulo());
        });
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.listarDetalhado(id);
        if (!livro.isPresent()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new LivroDetalheDto(livro.get()));
    }

}
