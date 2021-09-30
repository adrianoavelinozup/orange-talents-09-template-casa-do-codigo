package br.com.zupacademy.adriano.casadocodigo.controller.dto;

import br.com.zupacademy.adriano.casadocodigo.model.Autor;

public class AutorDetalheDto {
    private String nome;
    private String descricao;

    public AutorDetalheDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
