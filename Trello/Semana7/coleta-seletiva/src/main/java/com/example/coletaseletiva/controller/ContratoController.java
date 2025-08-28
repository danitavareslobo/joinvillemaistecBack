package com.example.coletaseletiva.controller;

import com.example.coletaseletiva.entity.Contrato;
import com.example.coletaseletiva.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratos")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @PostMapping
    public Contrato registrarContrato(@RequestBody Contrato contrato) {
        return contratoService.registrarContrato(contrato);
    }

    @GetMapping
    public List<Object> consultarContratos() {
        return contratoService.consultarContratos();
    }
}