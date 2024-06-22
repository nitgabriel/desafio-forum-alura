package br.com.alura.desafioforumalura.domain.cursos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    @Query("SELECT c FROM Curso c WHERE LOWER(c.nome) = LOWER(:nomeCurso)")
    Curso localizarCurso(String nomeCurso);

    @Query("SELECT c FROM Curso c WHERE LOWER(c.categoria) = LOWER(:categoria)")
    Page<Curso> findAllByCategoriaIgnoreCase(String categoria, Pageable pageable);
}