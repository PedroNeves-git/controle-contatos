package br.com.capacitacao.controle_contatos.exception;

public class ContatoException extends RuntimeException {

    public ContatoException(String message) {
        super(message);
    }

    public ContatoException(String message, Throwable cause) {
        super(message, cause);
    }
}
