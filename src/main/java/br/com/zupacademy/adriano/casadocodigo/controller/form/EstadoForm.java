package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.ExisteId;
import br.com.zupacademy.adriano.casadocodigo.annotation.NomeDeEstadoUnicoPorPais;
import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Estado;
import br.com.zupacademy.adriano.casadocodigo.model.Pais;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@NomeDeEstadoUnicoPorPais
public class EstadoForm {

    @NotEmpty
    @JsonProperty
    private String nome;

    @NotNull
    @ExisteId(classeDaEntidade = Pais.class, nomeDoCampo = "id")
    @JsonProperty
    private Long idPais;

    public Estado toModel(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, idPais);
        return new Estado(this.nome, pais);
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }
}
