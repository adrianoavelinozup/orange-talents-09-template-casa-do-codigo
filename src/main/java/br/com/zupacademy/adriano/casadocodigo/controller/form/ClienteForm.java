package br.com.zupacademy.adriano.casadocodigo.controller.form;

import br.com.zupacademy.adriano.casadocodigo.annotation.CpfOuCnpj;
import br.com.zupacademy.adriano.casadocodigo.annotation.EstadoValido;
import br.com.zupacademy.adriano.casadocodigo.annotation.ExisteId;
import br.com.zupacademy.adriano.casadocodigo.annotation.ValorUnico;
import br.com.zupacademy.adriano.casadocodigo.model.Cliente;
import br.com.zupacademy.adriano.casadocodigo.model.Estado;
import br.com.zupacademy.adriano.casadocodigo.model.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EstadoValido
public class ClienteForm {
    @NotEmpty
    @Email
    @ValorUnico(classeDaEntidade = Cliente.class, nomeDoCampo = "email")
    private String email;

    @NotEmpty
    private String nome;

    @NotEmpty
    private String sobrenome;

    @NotEmpty
    @CpfOuCnpj(classeDaEntidade = Cliente.class, nomeDoCampo = "documento")
    private String documento;

    @NotEmpty
    private String endereco;

    @NotEmpty
    private String complemento;

    @NotEmpty
    private String cidade;

    @NotNull
    @ExisteId(classeDaEntidade = Pais.class, nomeDoCampo = "id")
    private Long paisId;

    @ExisteId(classeDaEntidade = Estado.class, nomeDoCampo = "id")
    private Long estadoId;

    @NotEmpty
    private String telefone;

    @NotEmpty
    private String cep;

    public ClienteForm(@NotEmpty @Email String email, @NotEmpty String nome,
                       @NotEmpty String sobrenome,
                       @NotEmpty String documento, @NotEmpty String endereco, @NotEmpty String complemento,
                       @NotEmpty String cidade, @NotNull Long paisId, Long estadoId,
                       @NotEmpty String telefone,@NotEmpty String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.paisId = paisId;
        this.estadoId = estadoId;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getPaisId() {
        return paisId;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Cliente toModel(EntityManager entityManager) {
        Pais pais = entityManager.find(Pais.class, paisId);
        Estado estado = null;
        if (estadoId != null) {
            estado = entityManager.find(Estado.class, estadoId);
        }
        return new Cliente(email, nome, sobrenome, documento, endereco, complemento,
                cidade, pais, estado, telefone, cep);
    }
}
