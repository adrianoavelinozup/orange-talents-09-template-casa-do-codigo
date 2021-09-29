package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Autor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AutorForm {

    @NotEmpty
    private String nome;

    @NotEmpty
    @Email
    @ValorUnico(nomeDoCampo = "email", classeDaEntidade = Autor.class, message = "Email já está cadastrado. Escolha outro email")
    private String email;

    @NotEmpty
    @Size(min = 1, max = 400)
    private String descricao;

    public AutorForm(@NotEmpty String nome, @NotEmpty @Email String email,
                     @NotEmpty @Size(min = 1, max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor toModel() {
        return new Autor(nome, email,descricao);
    }

    public String getEmail() {
        return this.email;
    }
}
