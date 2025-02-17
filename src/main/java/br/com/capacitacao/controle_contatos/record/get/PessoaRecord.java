package br.com.capacitacao.controle_contatos.record.get;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public record PessoaRecord(
    Long id,
    String nome,          
    String endereco,      
    String cep,           
    String cidade,        
    String UF,            
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    Date dataNascimento, 
    String email          
) {}
