package br.com.zupacademy.adriano.casadocodigo.repository;

import br.com.zupacademy.adriano.casadocodigo.model.Livro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface LivroRepository extends PagingAndSortingRepository<Livro, Long> {
    @Query("SELECT livro FROM Livro livro " +
            "LEFT JOIN FETCH livro.autor a " +
            "LEFT JOIN FETCH livro.categoria c " +
            "WHERE livro.id = :id")
    Optional<Livro> listarDetalhado(Long id);
}
