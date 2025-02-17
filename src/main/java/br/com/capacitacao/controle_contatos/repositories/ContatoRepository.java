package br.com.capacitacao.controle_contatos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.capacitacao.controle_contatos.models.Contato;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
    List<Contato> findAllContatoByPessoaId(Long idPessoa);
}
