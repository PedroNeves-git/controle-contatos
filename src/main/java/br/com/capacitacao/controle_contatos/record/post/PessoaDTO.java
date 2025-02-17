package br.com.capacitacao.controle_contatos.record.post;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public record PessoaDTO(
        String nome,
        String endereco,
        String cep,
        String cidade,
        @DateTimeFormat(pattern = "dd-MM-yyyy")
        Date dataNascimento,
        String email,
        String uf
) {}

