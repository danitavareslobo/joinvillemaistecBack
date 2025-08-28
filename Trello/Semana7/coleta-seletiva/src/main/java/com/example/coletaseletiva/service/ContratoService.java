package com.example.coletaseletiva.service;

import com.example.coletaseletiva.entity.Contrato;
import com.example.coletaseletiva.entity.Coletor;
import com.example.coletaseletiva.entity.Rota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContratoService {

    @Autowired
    private ColetorService coletorService;

    @Autowired
    private RotaService rotaService;

    private static List<Contrato> contratos = new ArrayList<>();
    private static Long proximoId = 1L;

    public Contrato registrarContrato(Contrato contrato) {
        if (!coletorExiste(contrato.getColetorId()) || !rotaExiste(contrato.getRotaId())) {
            throw new RuntimeException("Coletor ou Rota não encontrados");
        }

        contrato.setId(proximoId++);
        contratos.add(contrato);
        return contrato;
    }

    public List<Object> consultarContratos() {
        List<Object> resultado = new ArrayList<>();

        for (Contrato contrato : contratos) {
            Coletor coletor = buscarColetorPorId(contrato.getColetorId());
            Rota rota = buscarRotaPorId(contrato.getRotaId());

            ContratoDetalhado detalhe = new ContratoDetalhado(
                    contrato.getId(),
                    coletor,
                    rota
            );
            resultado.add(detalhe);
        }

        return resultado;
    }

    private boolean coletorExiste(Long coletorId) {
        return buscarColetorPorId(coletorId) != null;
    }

    private boolean rotaExiste(Long rotaId) {
        return buscarRotaPorId(rotaId) != null;
    }

    private Coletor buscarColetorPorId(Long id) {
        return coletorService.consultarTodosColetores().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private Rota buscarRotaPorId(Long id) {
        return rotaService.consultarTodasRotas().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static class ContratoDetalhado {
        private Long contratoId;
        private Coletor coletor;
        private Rota rota;

        public ContratoDetalhado(Long contratoId, Coletor coletor, Rota rota) {
            this.contratoId = contratoId;
            this.coletor = coletor;
            this.rota = rota;
        }

        public Long getContratoId() { return contratoId; }
        public Coletor getColetor() { return coletor; }
        public Rota getRota() { return rota; }
    }
}