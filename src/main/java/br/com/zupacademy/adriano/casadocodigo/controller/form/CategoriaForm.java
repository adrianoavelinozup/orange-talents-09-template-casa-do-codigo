package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class CategoriaForm {
    @NotEmpty
    @JsonProperty
    @ValorUnico(classeDaEntidade = Categoria.class, nomeDoCampo = "nome", message = "Nome da categoria duplicado. Escolha outro nome")
    private String nome;

    public String getNome() {
        return this.nome;
    }

    public Categoria toModel() {
        return new Categoria(nome.trim());
    }
}
