package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.Existe;
import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Autor;
import br.com.zupacademy.adriano.casadocodigo.model.Categoria;
import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import br.com.zupacademy.adriano.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.adriano.casadocodigo.repository.CategoriaRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class LivroForm {

    @NotEmpty
    @ValorUnico(nomeDoCampo = "isbn", classeDaEntidade = Livro.class,
            message = "O livro com este ISBN já está cadastrado" )
    @JsonProperty
    private String isbn;

    @NotEmpty
    @ValorUnico(nomeDoCampo = "titulo", classeDaEntidade = Livro.class,
            message = "Este título já está cadastrado. Escolha outro título ")
    @JsonProperty
    private String titulo;

    @NotEmpty
    @Size(min = 1, max = 400)
    @JsonProperty
    private String resumo;

    @NotEmpty
    @JsonProperty
    private String sumario;

    @NotNull
    @Min(20)
    @JsonProperty
    private BigDecimal preco;

    @Range(min = 100)
    @NotNull
    @JsonProperty
    private Integer numeroPaginas;

    @Future
    @JsonProperty
    private LocalDate dataLancamento;

    @NotEmpty
    @JsonProperty
    @Existe(nomeDoCampo = "nome", classeDaEntidade = Categoria.class,
            message = "Não encontrada")
    private String nomeDaCategoria;

    @NotEmpty @Email
    @Existe(nomeDoCampo = "email", classeDaEntidade = Autor.class)
    @JsonProperty
    private String emailDoAutor;

    public Livro toMap(AutorRepository autorRepository, CategoriaRepository categoriaRepository) {
        Optional<Autor> autor = autorRepository.findByEmail(emailDoAutor);
        Optional<Categoria> categoria = categoriaRepository.findByNome(nomeDaCategoria);
        return new Livro(isbn, titulo, resumo, sumario, preco,
                numeroPaginas, dataLancamento, categoria.get(), autor.get());
    }
}
