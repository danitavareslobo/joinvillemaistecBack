package com.example.coletaseletiva.controller;


import com.example.coletaseletiva.entity.Rota;
import com.example.coletaseletiva.service.RotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    @Autowired
    private RotaService rotaService;

    // Cadastrar novos registros (POST)
    @PostMapping
    public Rota cadastrarRota(@RequestBody Rota rota) {
        return rotaService.cadastrarRota(rota);
    }

    // Consultar todos os registros (GET)
    @GetMapping
    public List<Rota> consultarTodasRotas() {
        return rotaService.consultarTodasRotas();
    }
}