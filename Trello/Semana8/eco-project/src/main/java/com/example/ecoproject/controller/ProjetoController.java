package com.example.ecoproject.controller;

import com.example.ecoproject.entity.Projeto;
import com.example.ecoproject.repository.ProjetoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @PostMapping
    public Projeto criarProjeto(@Valid @RequestBody Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    @GetMapping
    public List<Projeto> listarProjetos(
            @RequestParam(required = false) String regiao,
            @RequestParam(required = false) String nomeOrganizacao) {

        if (regiao != null || nomeOrganizacao != null) {
            return projetoRepository.findByRegiaoAndNomeOrganizacao(regiao, nomeOrganizacao);
        }
        return projetoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> buscarProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        return projeto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projeto> atualizarProjeto(@PathVariable Long id,
                                                    @Valid @RequestBody Projeto projetoAtualizado) {
        Optional<Projeto> projetoExistente = projetoRepository.findById(id);
        if (projetoExistente.isPresent()) {
            projetoAtualizado.setId(id);
            return ResponseEntity.ok(projetoRepository.save(projetoAtualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProjeto(@PathVariable Long id) {
        if (projetoRepository.existsById(id)) {
            projetoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}