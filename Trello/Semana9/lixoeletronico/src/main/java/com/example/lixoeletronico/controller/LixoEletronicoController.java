package com.example.lixoeletronico.controller;

import com.example.lixoeletronico.entity.LixoEletronico;
import com.example.lixoeletronico.service.LixoEletronicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/electronic-waste")
@CrossOrigin(origins = "*")
public class LixoEletronicoController {

    @Autowired
    private LixoEletronicoService service;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody LixoEletronico lixoEletronico) {
        try {
            LixoEletronico novoLixo = service.criar(lixoEletronico);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoLixo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<LixoEletronico>> buscarTodos(
            @RequestParam(required = false) String tipo) {

        List<LixoEletronico> lista;

        if (tipo != null && !tipo.trim().isEmpty()) {
            // Filtrar por tipo
            lista = service.buscarPorTipo(tipo);
        } else {
            // Buscar todos
            lista = service.buscarTodos();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<LixoEletronico> lixo = service.buscarPorId(id);

            if (lixo.isPresent()) {
                return ResponseEntity.ok(lixo.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Lixo eletrônico não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LixoEletronico lixoAtualizado) {

        try {
            LixoEletronico lixo = service.atualizar(id, lixoAtualizado);
            return ResponseEntity.ok(lixo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            service.deletar(id);
            return ResponseEntity.ok("Lixo eletrônico deletado com sucesso!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existe(@PathVariable Long id) {
        boolean existe = service.existe(id);
        return ResponseEntity.ok(existe);
    }
}