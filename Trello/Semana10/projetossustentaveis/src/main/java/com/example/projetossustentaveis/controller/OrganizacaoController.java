package com.example.projetossustentaveis.controller;

import com.example.projetossustentaveis.entity.Organizacao;
import com.example.projetossustentaveis.service.OrganizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrganizacaoController {

    @Autowired
    private OrganizacaoService organizacaoService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Organizacao>> getAllOrganizations() {
        List<Organizacao> organizacoes = organizacaoService.findAll();
        return ResponseEntity.ok(organizacoes);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Organizacao> getOrganizationById(@PathVariable Long id) {
        try {
            Organizacao organizacao = organizacaoService.findById(id);
            return ResponseEntity.ok(organizacao);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Organizacao> createOrganization(@Valid @RequestBody Organizacao organizacao) {
        try {
            Organizacao novaOrganizacao = organizacaoService.save(organizacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaOrganizacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Organizacao> updateOrganization(@PathVariable Long id, @Valid @RequestBody Organizacao organizacao) {
        try {
            Organizacao organizacaoAtualizada = organizacaoService.update(id, organizacao);
            return ResponseEntity.ok(organizacaoAtualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        try {
            organizacaoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}