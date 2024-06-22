package br.com.alura.desafioforumalura.domain.topicos;

import br.com.alura.desafioforumalura.domain.cursos.DadosDetalhamentoCurso;
import br.com.alura.desafioforumalura.domain.usuario.DadosDetalhamentoUsuario;

public record DadosListagemTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  DadosDetalhamentoUsuario autor,
                                  DadosDetalhamentoCurso curso,
                                  Boolean status
) {
    public DadosListagemTopico(Long id,
                               String titulo,
                               String mensagem,
                               DadosDetalhamentoUsuario autor,
                               DadosDetalhamentoCurso curso,
                               Boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.status = status;
    }}