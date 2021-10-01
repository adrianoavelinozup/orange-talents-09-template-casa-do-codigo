package br.com.zupacademy.adriano.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    @Deprecated
    public Estado() {
    }

    public Estado(@NotEmpty String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }
}
