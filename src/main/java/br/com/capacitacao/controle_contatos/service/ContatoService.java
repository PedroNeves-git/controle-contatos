package br.com.capacitacao.controle_contatos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capacitacao.controle_contatos.exception.ContatoException;
import br.com.capacitacao.controle_contatos.exception.NotFoundException;
import br.com.capacitacao.controle_contatos.models.Contato;
import br.com.capacitacao.controle_contatos.models.Pessoa;
import br.com.capacitacao.controle_contatos.repositories.ContatoRepository;
import br.com.capacitacao.controle_contatos.repositories.PessoaRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;
	
	@Autowired //Injeção de dependência
	private PessoaRepository pessoaRepository;
	
	
	//CRUD CONTATO
	public Contato save(Contato contato, Long pessoaId) {
		// Verifica se a pessoa existe antes de salvar o contato
        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NotFoundException("Pessoa não encontrada para o ID: " + pessoaId));

        contato.setPessoa(pessoa);

        try {
            return contatoRepository.save(contato);
        } catch (Exception e) {
            throw new ContatoException("Erro ao salvar contato!", e);
        }
    }
	
	 // ATUALIZAR CONTATO
    public Contato update(Long id, Contato contatoAtualizado) {
        Contato contatoExistente = contatoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contato não encontrado: ID " + id));

        contatoExistente.setTipoContato(contatoAtualizado.getTipoContato());
        contatoExistente.setContato(contatoAtualizado.getContato());

        return contatoRepository.save(contatoExistente);
    }
	
    // BUSCAR CONTATO POR ID
    public Optional<Contato> findById(Long id) {
        return contatoRepository.findById(id);
    }

    // LISTAR TODOS OS CONTATOS
    public List<Contato> findAll() {
        return contatoRepository.findAll();
    }

    public List<Contato> findAllContatoByIdPessoa(Long idPessoa) {
        return contatoRepository.findAllContatoByPessoaId(idPessoa);
    }

    // DELETAR CONTATO POR ID
    public void delete(Long id) {
        if (!contatoRepository.existsById(id)) {
            throw new NotFoundException("Contato não encontrado: ID " + id);
        }
        contatoRepository.deleteById(id);
    }
}
