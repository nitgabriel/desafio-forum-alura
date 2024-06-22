package br.com.alura.desafioforumalura.domain;

public class TratamentoDeException extends RuntimeException {
    public TratamentoDeException(String mensagem) {
        super(mensagem);
    }
}