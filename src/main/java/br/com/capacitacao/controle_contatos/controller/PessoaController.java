package br.com.capacitacao.controle_contatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capacitacao.controle_contatos.business.PessoaService;
import br.com.capacitacao.controle_contatos.controller.dto.PessoaMalaDiretaRecord;
import br.com.capacitacao.controle_contatos.controller.dto.PessoaRecord;
import br.com.capacitacao.controle_contatos.infrastructure.entities.Pessoa;

@RestController
@RequestMapping("/api/pessoas")//http://localhost:8080/api/pessoa
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	//insere dados de uma nova pessoa
	@PostMapping //http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> save(@RequestBody Pessoa pessoa){
		Pessoa novaPessoa = pessoaService.save(pessoa);
		
		if(novaPessoa == null) {
			return ResponseEntity.badRequest().build(); //status 404
		}else {
			return ResponseEntity.ok(novaPessoa); //status 200
		}
	}
	
	//Busca dados sobre todas as pessoas
	@GetMapping // GET http://localhost:8080/api/pessoas
	public ResponseEntity<List<PessoaRecord>> findAll() {
	    List<Pessoa> pessoas = pessoaService.findAll();
	    
	    if (pessoas.isEmpty()) {
	        return ResponseEntity.notFound().build(); // Retorna 404 caso não tenha pessoas cadastradas
	    }

	    // Converte List<Pessoa> para List<PessoaRecord>
	    List<PessoaRecord> pessoaRecords = pessoas.stream()
	            .map(p -> new PessoaRecord(
	                    p.getId(),
	                    p.getNome(),
	                    p.getEndereco(),
	                    p.getCep(),
	                    p.getCidade(),
	                    p.getUF(),
	                    p.getDataNascimento(),
	                    p.getEmail()
	            ))
	            .toList();

	    return ResponseEntity.ok(pessoaRecords); // Retorna 200 OK com os dados no formato correto
	}

	
	@GetMapping("/{id}")// GET http://localhost:8080/api/pessoas/{id}
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
	    Optional<Pessoa> pessoa = pessoaService.findById(id);
	    return pessoa.map(ResponseEntity::ok)
	                 .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/maladireta/{id}") // GET http://localhost:8080/api/pessoas/maladireta/{id}
	public ResponseEntity<PessoaMalaDiretaRecord> findByIdMalaDireta(@PathVariable Long id) {
	    PessoaMalaDiretaRecord pessoaMalaDireta = pessoaService.findMalaDiretaById(id);
	    return ResponseEntity.ok(pessoaMalaDireta); //status 200 OK
	}
	
	@PutMapping // PUT http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
	    Pessoa atualizaPessoa = pessoaService.update(pessoa);
	    return ResponseEntity.ok(atualizaPessoa); // status 200, com dados atualizados
	}

	@DeleteMapping("/{id}") // DELETE http://localhost:8080/api/pessoas/{id}
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    if (pessoaService.findById(id).isEmpty()) { // Verifica se existe antes.
	        return ResponseEntity.notFound().build(); // Retorna 404 se não existir.
	    }
	    pessoaService.delete(id);
	    return ResponseEntity.noContent().build(); // status 204 se deletado com sucesso.
	}

}
