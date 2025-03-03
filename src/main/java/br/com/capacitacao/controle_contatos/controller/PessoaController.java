package br.com.capacitacao.controle_contatos.controller;

import java.util.List;
import java.util.Optional;

import br.com.capacitacao.controle_contatos.record.post.PessoaDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.capacitacao.controle_contatos.record.get.PessoaMalaDiretaRecord;
import br.com.capacitacao.controle_contatos.record.get.PessoaRecord;
import br.com.capacitacao.controle_contatos.models.Pessoa;
import br.com.capacitacao.controle_contatos.service.PessoaService;

@RestController
@RequestMapping("/api/pessoas")//http://localhost:8080/api/pessoa
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	//insere dados de uma nova pessoa
	@Operation(summary = "Cria uma nova pessoa",
			description = "Adiciona um novo registro de pessoa no sistema. Exemplo: `POST /api/pessoas`")
	@PostMapping //http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> save(@RequestBody PessoaDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(pessoaDTO.nome());
		pessoa.setEndereco(pessoaDTO.endereco());
		pessoa.setCep(pessoaDTO.cep());
		pessoa.setCidade(pessoaDTO.cidade());
		pessoa.setDataNascimento(pessoaDTO.dataNascimento());
		pessoa.setEmail(pessoaDTO.email());
		pessoa.setUF(pessoaDTO.uf());

		Pessoa novaPessoa = pessoaService.save(pessoa);

		return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
	}


	//Busca dados sobre todas as pessoas
	@Operation(summary = "Lista todas as pessoas",
			description = "Retorna uma lista contendo todas as pessoas cadastradas no sistema. Exemplo: `GET /api/pessoas`")
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

	@Operation(summary = "Busca uma pessoa por ID",
			description = "Retorna os detalhes de uma pessoa específica pelo ID informado. Exemplo: `GET /api/pessoas/{id}`")
	@GetMapping("/{id}")// GET http://localhost:8080/api/pessoas/{id}
	public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
	    Optional<Pessoa> pessoa = pessoaService.findById(id);
	    return pessoa.map(ResponseEntity::ok)
	                 .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Operation(summary = "Busca os dados de mala direta de uma pessoa",
			description = "Retorna os dados formatados para mala direta de uma pessoa específica pelo ID informado. Exemplo: `GET /api/pessoas/maladireta/{id}`")
	@GetMapping("/maladireta/{id}") // GET http://localhost:8080/api/pessoas/maladireta/{id}
	public ResponseEntity<PessoaMalaDiretaRecord> findByIdMalaDireta(@PathVariable Long id) {
	    PessoaMalaDiretaRecord pessoaMalaDireta = pessoaService.findMalaDiretaById(id);
	    return ResponseEntity.ok(pessoaMalaDireta); //status 200 OK
	}

	@Operation(summary = "Atualiza os dados de uma pessoa",
			description = "Atualiza um registro existente de pessoa no sistema. Exemplo: `PUT /api/pessoas`")
	@PutMapping // PUT http://localhost:8080/api/pessoas
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
	    Pessoa atualizaPessoa = pessoaService.update(pessoa);
	    return ResponseEntity.ok(atualizaPessoa); // status 200, com dados atualizados
	}

	@Operation(summary = "Remove uma pessoa",
			description = "Deleta um registro de pessoa com base no ID fornecido. Exemplo: `DELETE /api/pessoas/{id}`")
	@DeleteMapping("/{id}") // DELETE http://localhost:8080/api/pessoas/{id}
	public ResponseEntity<Void> delete(@PathVariable Long id) {
	    if (pessoaService.findById(id).isEmpty()) { // Verifica se existe antes.
	        return ResponseEntity.notFound().build(); // Retorna 404 se não existir.
	    }
	    pessoaService.delete(id);
	    return ResponseEntity.noContent().build(); // status 204 se deletado com sucesso.
	}

}
