package br.com.capacitacao.controle_contatos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.capacitacao.controle_contatos.infrastructure.models.Contato;
import br.com.capacitacao.controle_contatos.service.ContatoService;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    // POST - Adiciona um novo contato a uma pessoa
    @PostMapping("/{pessoaId}")
    public ResponseEntity<Contato> addContato(@PathVariable Long pessoaId, @RequestBody Contato contato) {
        Contato novoContato = contatoService.save(contato, pessoaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoContato);
    }

    // GET - Retorna os dados de um contato por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoById(@PathVariable Long id) {
        Optional<Contato> contato = contatoService.findById(id);
        return contato.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET - Lista todos os contatos de uma pessoa
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<Contato>> getContatosByPessoa(@PathVariable Long idPessoa) {
        List<Contato> contatos = contatoService.findAll();
        return ResponseEntity.ok(contatos);
    }

    // PUT - Atualiza um contato existente
    @PutMapping("/{id}")
    public ResponseEntity<Contato> updateContato(@PathVariable Long id, @RequestBody Contato contato) {
        Contato contatoAtualizado = contatoService.update(id, contato);
        return ResponseEntity.ok(contatoAtualizado);
    }

    // DELETE - Remove um contato por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContato(@PathVariable Long id) {
        contatoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
