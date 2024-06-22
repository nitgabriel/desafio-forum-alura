package br.com.alura.desafioforumalura.domain.topicos;


import br.com.alura.desafioforumalura.domain.usuario.DadosDetalhamentoUsuario;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        DadosDetalhamentoUsuario autor,
        @NotBlank
        String nomeCurso) {
}