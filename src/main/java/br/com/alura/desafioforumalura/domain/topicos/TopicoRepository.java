package br.com.alura.desafioforumalura.domain.topicos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitulo(String titulo);

    boolean existsByMensagem(String mensagem);

    @Query("SELECT t FROM Topico t WHERE t.curso.nome = :cursoNome AND FUNCTION('YEAR', t.dataCriacao) = :ano")
    Page<Topico> findByCursoNomeAndAno(String cursoNome, int ano, Pageable pageable);

    @Query("SELECT t FROM Topico t ORDER BY t.dataCriacao ASC")
    Page<Topico> findAllByOrderByDataCriacaoAsc(Pageable pageable);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    Page<Topico> findByStatusTrue(Pageable pageable);

    Optional<Topico> findByIdAndStatusTrue(Long id);
}