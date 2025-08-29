package com.example.ecoproject.controller;

import com.example.ecoproject.entity.Organizacao;
import com.example.ecoproject.repository.OrganizacaoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/organizations")
public class OrganizacaoController {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    @PostMapping
    public Organizacao criarOrganizacao(@Valid @RequestBody Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }

    @GetMapping
    public List<Organizacao> listarOrganizacoes(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String contato) {

        if (nome != null || contato != null) {
            return organizacaoRepository.findByNomeAndContatoContaining(nome, contato);
        }
        return organizacaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizacao> buscarOrganizacao(@PathVariable Long id) {
        Optional<Organizacao> organizacao = organizacaoRepository.findById(id);
        return organizacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizacao> atualizarOrganizacao(@PathVariable Long id,
                                                            @Valid @RequestBody Organizacao organizacaoAtualizada) {
        Optional<Organizacao> organizacaoExistente = organizacaoRepository.findById(id);
        if (organizacaoExistente.isPresent()) {
            organizacaoAtualizada.setId(id);
            return ResponseEntity.ok(organizacaoRepository.save(organizacaoAtualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirOrganizacao(@PathVariable Long id) {
        if (organizacaoRepository.existsById(id)) {
            organizacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}