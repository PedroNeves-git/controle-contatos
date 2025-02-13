package br.com.capacitacao.controle_contatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	// Tratando exceções de Pessoa não encontrada
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<String> handlePessoaNotFound(PessoaNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Tratando exceções gerais de Pessoa (pode ser erro de validação, etc)
    @ExceptionHandler(PessoaException.class)
    public ResponseEntity<String> handlePessoaException(PessoaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Captura de qualquer outra exceção inesperada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("Erro interno do servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
 // Tratando exceções de Contato não encontrado
    @ExceptionHandler(ContatoNotFoundException.class)
    public ResponseEntity<String> handleContatoNotFound(ContatoNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Tratando exceções gerais de Contato
    @ExceptionHandler(ContatoException.class)
    public ResponseEntity<String> handleContatoException(ContatoException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

