package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.ExisteId;
import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Autor;
import br.com.zupacademy.adriano.casadocodigo.model.Categoria;
import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Range;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
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
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    @NotNull
    @JsonProperty
    @ExisteId(nomeDoCampo = "id", classeDaEntidade = Categoria.class,
            message = "Não encontrada")
    private Long idCategoria;

    @NotNull
    @ExisteId(nomeDoCampo = "id", classeDaEntidade = Autor.class)
    @JsonProperty
    private Long idAutor;

    public Livro toMap(EntityManager entityManager) {
        Autor autor = entityManager.find(Autor.class, idAutor);
        Categoria categoria = entityManager.find(Categoria.class, idCategoria);

        Assert.state(autor!=null,"Você esta querendo cadastrar um livro para um autor que não existe no banco da dados, com id  " + idAutor);
        Assert.state(categoria!=null,"Você esta querendo cadastrar um livro para uma categoria que não existe no banco de dados, com id " + idCategoria);

        return new Livro(isbn, titulo, resumo, sumario, preco,
                numeroPaginas, dataLancamento, categoria, autor);
    }
}
