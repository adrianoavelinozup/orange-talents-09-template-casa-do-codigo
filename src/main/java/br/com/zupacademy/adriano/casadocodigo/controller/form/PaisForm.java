package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class PaisForm {
    @NotEmpty
    @JsonProperty
    @ValorUnico(nomeDoCampo = "nome", classeDaEntidade = Pais.class)
    private String nome;

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
