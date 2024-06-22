package br.com.alura.desafioforumalura.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String nome,
        String email,
        Boolean status
) {

    public DadosDetalhamentoUsuario(Long id, String nome, String email) {
        this(id,nome,email,false);
    }
}