package br.com.capacitacao.controle_contatos.exception;

public class ContatoNotFoundException extends RuntimeException {

    public ContatoNotFoundException(String message) {
        super(message);
    }
}
