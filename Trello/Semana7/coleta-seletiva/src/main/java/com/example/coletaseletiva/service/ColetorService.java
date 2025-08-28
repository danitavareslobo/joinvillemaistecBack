package com.example.coletaseletiva.service;

import com.example.coletaseletiva.entity.Coletor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ColetorService {

    private static List<Coletor> coletores = new ArrayList<>();
    private static Long proximoId = 1L;

    public Coletor cadastrarColetor(Coletor coletor) {
        coletor.setId(proximoId++);
        coletores.add(coletor);
        return coletor;
    }

    public List<Coletor> consultarTodosColetores() {
        return new ArrayList<>(coletores);
    }

    public static Long getProximoId() {
        return proximoId;
    }
}