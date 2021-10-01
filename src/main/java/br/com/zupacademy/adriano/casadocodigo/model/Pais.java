package br.com.zupacademy.adriano.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "paises", uniqueConstraints = {
    @UniqueConstraint(name = "uc_pais_nome", columnNames = "nome")
})
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @Deprecated
    public Pais() {
    }

    public Pais(@NotEmpty String nome) {
        this.nome = nome;
    }
}
