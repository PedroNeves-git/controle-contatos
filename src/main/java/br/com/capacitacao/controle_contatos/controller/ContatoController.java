package br.com.capacitacao.controle_contatos.controller;

import java.util.List;
import java.util.Optional;

import br.com.capacitacao.controle_contatos.record.post.ContatoDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.capacitacao.controle_contatos.models.Contato;
import br.com.capacitacao.controle_contatos.service.ContatoService;

@RestController
@RequestMapping("/api/contatos")
@CrossOrigin(origins = "http://localhost:4200")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    // POST - Adiciona um novo contato a uma pessoa
    @Operation(summary = "Adiciona um novo contato a uma pessoa",
            description = "Cria um contato associado a uma pessoa específica pelo ID.\n" +
            "Tipos de Contato: TELEFONE, CELULAR, EMAIL, REDESOCIAL")
    @PostMapping("/{pessoaId}")
    public ResponseEntity<Contato> addContato(@PathVariable Long pessoaId, @RequestBody ContatoDTO contatoDTO) {
        Contato contato = new Contato();
        contato.setTipoContato(contatoDTO.getTipoContato());
        contato.setContato(contatoDTO.getContato());
        contato.setPessoa(contatoDTO.getPessoa());

        Contato novoContato = contatoService.save(contato, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    // GET - Retorna os dados de um contato por ID
    @Operation(summary = "Busca um contato por ID",
            description = "Retorna os detalhes de um contato específico pelo ID informado. Exemplo: `GET /api/contatos/{id}`")
    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.findById(id);
        return contato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET - Lista todos os contatos de uma pessoa
    @Operation(summary = "Lista todos os contatos de uma pessoa",
            description = "Retorna uma lista de todos os contatos cadastrados para uma determinada pessoa pelo ID dela. Exemplo: `GET /api/contatos/pessoa/{idPessoa}`")
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<Contato>> getContatosByPessoa(@PathVariable Long idPessoa) {
        List<Contato> contatos = contatoService.findAllContatoByIdPessoa(idPessoa);
        return ResponseEntity.ok(contatos);
    }

    // PUT - Atualiza um contato existente
    @Operation(summary = "Atualiza um contato existente",
            description = "Atualiza os dados de um contato pelo ID informado. Exemplo: `PUT /api/contatos/{id}`")
    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato) {
        Contato contatoAtualizado = contatoService.update(id, contato);
        return ResponseEntity.ok(contatoAtualizado);
    }

    // DELETE - Remove um contato por ID
    @Operation(summary = "Remove um contato",
            description = "Deleta um contato específico com base no ID fornecido. Exemplo: `DELETE /api/contatos/{id}`")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
