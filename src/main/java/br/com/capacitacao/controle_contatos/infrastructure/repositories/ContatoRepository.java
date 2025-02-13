package br.com.capacitacao.controle_contatos.infrastructure.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capacitacao.controle_contatos.infrastructure.models.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {

}
