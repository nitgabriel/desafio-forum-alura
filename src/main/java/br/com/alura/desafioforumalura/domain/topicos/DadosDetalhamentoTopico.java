package br.com.alura.desafioforumalura.domain.topicos;

import br.com.alura.desafioforumalura.domain.cursos.DadosDetalhamentoCurso;
import br.com.alura.desafioforumalura.domain.resposta.DadosDetalhamentoResposta;
import br.com.alura.desafioforumalura.domain.usuario.DadosDetalhamentoUsuario;

import java.util.List;

public record DadosDetalhamentoTopico(Long id,
                                      String titulo,
                                      String mensagem,
                                      DadosDetalhamentoUsuario autor,
                                      DadosDetalhamentoCurso curso,
                                      List<DadosDetalhamentoResposta> resposta,
                                      Boolean status
) {
    public DadosDetalhamentoTopico(Long id,
                                   String titulo,
                                   String mensagem,
                                   DadosDetalhamentoUsuario autor,
                                   DadosDetalhamentoCurso curso,
                                   List<DadosDetalhamentoResposta> resposta,
                                   Boolean status) {
        this.id = id;
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.resposta = resposta;
        this.status = status;
    }}