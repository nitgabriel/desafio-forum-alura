package br.com.alura.desafioforumalura.domain.resposta;

import br.com.alura.desafioforumalura.domain.topicos.Topico;
import br.com.alura.desafioforumalura.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DadosCadastroResposta(String mensagem, Boolean solucao, Long autorId, Long topicoId) {
    public Resposta toEntity(Usuario autor, Topico topico, LocalDateTime dataCriacao){
        Resposta resposta = new Resposta();
        resposta.setMensagem(this.mensagem());
        resposta.setSolucao(this.solucao());
        resposta.setAutor(autor);
        resposta.setTopico(topico);
        resposta.setDataCriacao(dataCriacao);
        return resposta;
    }
}