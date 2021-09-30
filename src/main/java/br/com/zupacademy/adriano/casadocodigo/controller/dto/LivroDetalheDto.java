package br.com.zupacademy.adriano.casadocodigo.controller.dto;

import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroDetalheDto {
    private String isbn;

    private String titulo;

    private String resumo;

    private String sumario;

    private BigDecimal preco;

    private Integer numeroPaginas;

    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    private String tituloDaCategoria;

    private String nomeDoAutor;

    public LivroDetalheDto(Livro livro) {
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.resumo  = livro.getResumo();
        this.sumario  = livro.getSumario();
        this.preco  = livro.getPreco();
        this.numeroPaginas  = livro.getNumeroPaginas();
        this.dataLancamento  = livro.getDataLancamento();
        this.tituloDaCategoria  = livro.getCategoria().getNome();
        this.nomeDoAutor  = livro.getAutor().getNome();
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
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

    public String getTituloDaCategoria() {
        return tituloDaCategoria;
    }

    public String getNomeDoAutor() {
        return nomeDoAutor;
    }
}
