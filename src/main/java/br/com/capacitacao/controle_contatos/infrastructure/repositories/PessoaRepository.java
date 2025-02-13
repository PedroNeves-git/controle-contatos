package br.com.capacitacao.controle_contatos.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capacitacao.controle_contatos.infrastructure.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
