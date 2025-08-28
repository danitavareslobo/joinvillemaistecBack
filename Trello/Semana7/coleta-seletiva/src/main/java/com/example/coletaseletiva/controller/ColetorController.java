package com.example.coletaseletiva.controller;

import com.example.coletaseletiva.entity.Coletor;
import com.example.coletaseletiva.service.ColetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coletores")
public class ColetorController {

    @Autowired
    private ColetorService coletorService;

    // Cadastrar novos registros (POST)
    @PostMapping
    public Coletor cadastrarColetor(@RequestBody Coletor coletor) {
        return coletorService.cadastrarColetor(coletor);
    }

    // Consultar todos os registros (GET)
    @GetMapping
    public List<Coletor> consultarTodosColetores() {
        return coletorService.consultarTodosColetores();
    }
}