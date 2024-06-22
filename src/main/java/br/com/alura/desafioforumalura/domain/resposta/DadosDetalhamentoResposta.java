package br.com.alura.desafioforumalura.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        LocalDateTime dataCriacao,
                                        Boolean solucao,
                                        Long autorId,
                                        Long topicoId) {
}