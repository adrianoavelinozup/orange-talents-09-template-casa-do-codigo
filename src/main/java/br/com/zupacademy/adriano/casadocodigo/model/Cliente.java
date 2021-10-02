package br.com.zupacademy.adriano.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clientes", uniqueConstraints = {
    @UniqueConstraint(name = "uc_cliente_email", columnNames = "email"),
    @UniqueConstraint(name = "uc_cliente_documento", columnNames = "documento")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Email
    @Column(nullable = false)
    private String email;

    @NotEmpty
    @Column(nullable = false)
    private String nome;

    @NotEmpty
    @Column(nullable = false)
    private String sobrenome;

    @NotEmpty
    @Column(nullable = false)
    private String documento;

    @NotEmpty
    @Column(nullable = false)
    private String endereco;

    @NotEmpty
    @Column(nullable = false)
    private String complemento;

    @NotEmpty
    @Column(nullable = false)
    private String cidade;

    @ManyToOne
    private Pais pais;

    @ManyToOne
    @JoinColumn(nullable = true)
    private Estado estado;

    @NotEmpty
    @Column(nullable = false)
    private String telefone;

    @NotEmpty
    @Column(nullable = false)
    private String cep;

    @Deprecated
    public Cliente() {
    }

    public Cliente(@NotEmpty @Email String email,
                   @NotEmpty String nome,
                   @NotEmpty String sobrenome,
                   @NotEmpty String documento,
                   @NotEmpty String endereco,
                   @NotEmpty String complemento,
                   @NotEmpty String cidade,
                   @NotNull Pais pais,
                   Estado estado,
                   @NotEmpty String telefone,
                   @NotEmpty String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return this.id;
    }
}
