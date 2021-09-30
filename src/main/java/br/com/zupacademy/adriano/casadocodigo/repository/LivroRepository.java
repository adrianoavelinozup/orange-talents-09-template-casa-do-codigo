package br.com.zupacademy.adriano.casadocodigo.repository;

import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {
}
