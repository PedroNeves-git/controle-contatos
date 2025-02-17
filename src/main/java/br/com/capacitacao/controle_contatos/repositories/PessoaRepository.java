package br.com.capacitacao.controle_contatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capacitacao.controle_contatos.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
