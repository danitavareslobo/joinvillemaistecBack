package com.example.coletaseletiva.service;

import com.example.coletaseletiva.entity.Rota;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class RotaService {

    private static List<Rota> rotas = new ArrayList<>();
    private static Long proximoId = 1L;

    public Rota cadastrarRota(Rota rota) {
        rota.setId(proximoId++);
        rotas.add(rota);
        return rota;
    }

    public List<Rota> consultarTodasRotas() {
        return new ArrayList<>(rotas);
    }

    public static Long getProximoId() {
        return proximoId;
    }
}