package br.com.capacitacao.controle_contatos.exception;

public class PessoaException extends RuntimeException {

    public PessoaException(String message) {
        super(message);
    }

    public PessoaException(String message, Throwable cause) {
        super(message, cause);
    }
}
