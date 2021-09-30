package br.com.zupacademy.adriano.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "autores", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email", name = "uc_email")
})
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotEmpty @Email
    @Column(nullable = false)
    private String email;

    @NotEmpty @Size(min = 1, max = 400)
    @Column(nullable = false)
    private String descricao;

    @NotNull
    private LocalDate dataCriacao = LocalDate.now();

    @Deprecated
    public Autor() {
    }

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
