package br.com.capacitacao.controle_contatos.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.capacitacao.controle_contatos.controller.dto.PessoaMalaDiretaRecord;
import br.com.capacitacao.controle_contatos.exception.PessoaException;
import br.com.capacitacao.controle_contatos.exception.PessoaNotFoundException;
import br.com.capacitacao.controle_contatos.infrastructure.entities.Pessoa;
import br.com.capacitacao.controle_contatos.infrastructure.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired //Injeção de dependência
	private PessoaRepository pessoaRepository;
	
	//CRUD Pessoa
	//salvar dados pessoais
	public Pessoa save(Pessoa pessoa) {
		//Verifica se o campo nome está vazio
		if (pessoa.getNome() == null || pessoa.getNome().isEmpty()) {
            throw new PessoaException("Nome da pessoa deve ser preenchido!");
        }
        try {
            return pessoaRepository.save(pessoa);
        } catch (Exception e) {
            throw new PessoaException("Erro ao salvar dados pessoais!", e);
        }
    }
	
	//Editar dados pessoais
	public Pessoa update(Pessoa pessoa) {
        Optional<Pessoa> findPessoa = pessoaRepository.findById(pessoa.getId());

        if (findPessoa.isEmpty()) {
            throw new PessoaNotFoundException("Dados pessoais não encontrados com base no ID: " + pessoa.getId());
        }

        Pessoa updPessoa = findPessoa.get();
        updPessoa.setNome(pessoa.getNome());
        updPessoa.setEndereco(pessoa.getEndereco());
        updPessoa.setCep(pessoa.getCep());
        updPessoa.setCidade(pessoa.getCidade());
        updPessoa.setUF(pessoa.getUF());
        updPessoa.setDataNascimento(pessoa.getDataNascimento());
        updPessoa.setEmail(pessoa.getEmail());

        return pessoaRepository.save(updPessoa);
    }
	
	//Filtrar pessoa com base no Id
	public Pessoa findById(Long id){
		//SELECT * FROM PESSOA WHERE ID = 
		//Busca pessoa com base no Id, caso não encontre gera o erro personalizado
		return pessoaRepository.findById(id)
				.orElseThrow(() -> new PessoaNotFoundException("Dados pessoais não encontrados com base no ID: " + id));
	}
	
	//Filtrar os dados pessoais com base no Id e concatena endereço
	public PessoaMalaDiretaRecord findMalaDiretaById(Long id) {
		//Busca pessoa com base no Id, caso não encontre gera o erro personalizado
        Pessoa pessoa = pessoaRepository.findById(id)
        		.orElseThrow(() -> new PessoaNotFoundException("Dados pessoais não encontrados com base no ID: " + id));
        
     // Concatena os dados necessários para a mala direta
        String endCepCidadeUf = String.format("%s, – CEP: %s – %s/%s", 
                pessoa.getEndereco(), 
                pessoa.getCep(),
                pessoa.getCidade(), 
                pessoa.getUF());

        return new PessoaMalaDiretaRecord(pessoa.getId(), pessoa.getNome(), endCepCidadeUf);
	}
	
	//Listar Todas as pessoas
	public List<Pessoa> findAll(){
		//SELECT * FROM PESSOA 
		return pessoaRepository.findAll();
	}
	
	//Deletar uma pessoa cadastrada por Id
	public void delete(Long id) {
		//DROP FROM PESSOA where id = ?id
		pessoaRepository.deleteById(id);
	}
}
