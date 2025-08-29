package com.example.lixoeletronico.controller;

import com.example.lixoeletronico.entity.PontoColeta;
import com.example.lixoeletronico.service.PontoColetaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/collection-points")
@CrossOrigin(origins = "*")
public class PontoColetaController {

    @Autowired
    private PontoColetaService service;

    @PostMapping
    public ResponseEntity<?> criar(@Valid @RequestBody PontoColeta pontoColeta) {
        try {
            PontoColeta novoPonto = service.criar(pontoColeta);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoPonto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PontoColeta>> buscarTodos(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String endereco,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam(required = false, defaultValue = "false") boolean apenasColataFutura) {

        List<PontoColeta> lista;

        if (nome != null && !nome.trim().isEmpty()) {
            lista = service.buscarPorNome(nome);
        } else if (endereco != null && !endereco.trim().isEmpty()) {
            lista = service.buscarPorEndereco(endereco);
        } else if (material != null && !material.trim().isEmpty()) {
            lista = service.buscarPorMaterialAceito(material);
        } else if (data != null) {
            lista = service.buscarPorDataColeta(data);
        } else if (dataInicio != null && dataFim != null) {
            lista = service.buscarPorPeriodo(dataInicio, dataFim);
        } else if (apenasColataFutura) {
            lista = service.buscarPontosColetaFutura();
        } else {
            lista = service.buscarTodos();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<PontoColeta> ponto = service.buscarPorId(id);

            if (ponto.isPresent()) {
                return ResponseEntity.ok(ponto.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Ponto de coleta não encontrado com ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PontoColeta pontoAtualizado) {

        try {
            PontoColeta ponto = service.atualizar(id, pontoAtualizado);
            return ResponseEntity.ok(ponto);
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
            return ResponseEntity.ok("Ponto de coleta deletado com sucesso!");
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

    @GetMapping("/future")
    public ResponseEntity<List<PontoColeta>> buscarColetaFutura() {
        List<PontoColeta> lista = service.buscarPontosColetaFutura();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/electronic-waste/name/{name}")
    public ResponseEntity<?> buscarPorNomeLixoEletronico(@PathVariable String name) {
        try {
            List<PontoColeta> pontos = service.buscarPorNomeLixoEletronico(name);

            if (pontos.isEmpty()) {
                return ResponseEntity.ok().body("Nenhum ponto de coleta encontrado que aceite lixo eletrônico contendo: " + name);
            }

            return ResponseEntity.ok(pontos);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erro: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro interno: " + e.getMessage());
        }
    }
}