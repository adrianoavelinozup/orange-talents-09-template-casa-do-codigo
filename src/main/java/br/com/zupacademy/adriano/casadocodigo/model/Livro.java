package br.com.zupacademy.adriano.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "livros", uniqueConstraints = {
    @UniqueConstraint(columnNames = "titulo", name = "uc_titulo_livro"),
    @UniqueConstraint(columnNames = "isbn", name = "uc_isbn_livro")
})
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String isbn;

    @NotEmpty
    @Column(nullable = false)
    private String titulo;

    @NotEmpty
    @Size(min = 1, max = 400)
    @Column(nullable = false)
    private String resumo;

    @NotEmpty
    @Column(nullable = false)
    @Lob
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @Min(value = 100)
    @NotNull
    private Integer numeroPaginas;

    @Future
    @Column(nullable = false)
    private LocalDate dataLancamento;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(String isbn, String titulo, String resumo, String sumario,
                 BigDecimal preco, Integer numeroPaginas, LocalDate dataLancamento,
                 Categoria categoria, Autor autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.dataLancamento = dataLancamento;
        this.categoria = categoria;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Autor getAutor() {
        return autor;
    }
}
