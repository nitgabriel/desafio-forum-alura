package br.com.alura.desafioforumalura.controller;

import br.com.alura.desafioforumalura.domain.cursos.CursoService;
import br.com.alura.desafioforumalura.domain.cursos.DadosCadastroCurso;
import br.com.alura.desafioforumalura.domain.cursos.DadosDetalhamentoCurso;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder){

        Long cursoId = cursoService.cadastrar(dados);
        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(cursoId).toUri();

        return ResponseEntity.created(uri).body("Curso registrado!");
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoCurso>> listar(Pageable pageable){
        Page<DadosDetalhamentoCurso> cursoPage = cursoService.getAllCursos(pageable);
        return ResponseEntity.ok(cursoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody DadosDetalhamentoCurso dados){
        cursoService.atualizar(id,dados);
        return ResponseEntity.ok("Curso atualizado!");
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<Page<DadosDetalhamentoCurso>> listarCursosPorCategoria(@PathVariable String categoria, Pageable pageable) {
        Page<DadosDetalhamentoCurso> cursosPage = cursoService.findByCategoria(categoria, pageable);
        return ResponseEntity.ok(cursosPage);
    }


}