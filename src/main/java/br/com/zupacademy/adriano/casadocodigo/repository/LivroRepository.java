package br.com.zupacademy.adriano.casadocodigo.repository;

import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, String> {
}
