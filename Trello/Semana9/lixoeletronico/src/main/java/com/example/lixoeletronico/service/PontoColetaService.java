package com.example.lixoeletronico.service;

import com.example.lixoeletronico.entity.PontoColeta;
import com.example.lixoeletronico.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PontoColetaService {

    @Autowired
    private PontoColetaRepository repository;

    public PontoColeta criar(PontoColeta pontoColeta) {
        if (repository.existsByNomeIgnoreCase(pontoColeta.getNome())) {
            throw new IllegalArgumentException("Já existe um ponto de coleta com este nome: " + pontoColeta.getNome());
        }

        if (pontoColeta.getDiaColeta().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de coleta não pode ser no passado");
        }

        return repository.save(pontoColeta);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarTodos() {
        return repository.findAllOrderByNome();
    }

    @Transactional(readOnly = true)
    public Optional<PontoColeta> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public PontoColeta buscarPorIdObrigatorio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ponto de coleta não encontrado com ID: " + id));
    }

    public PontoColeta atualizar(Long id, PontoColeta pontoAtualizado) {
        PontoColeta pontoExistente = buscarPorIdObrigatorio(id);

        // Validar se o novo nome já existe em outro registro
        if (!pontoExistente.getNome().equalsIgnoreCase(pontoAtualizado.getNome()) &&
                repository.existsByNomeIgnoreCase(pontoAtualizado.getNome())) {
            throw new IllegalArgumentException("Já existe um ponto de coleta com este nome: " + pontoAtualizado.getNome());
        }

        // Atualizar campos
        pontoExistente.setNome(pontoAtualizado.getNome());
        pontoExistente.setEndereco(pontoAtualizado.getEndereco());
        pontoExistente.setDiaColeta(pontoAtualizado.getDiaColeta());
        pontoExistente.setMateriaisAceitos(pontoAtualizado.getMateriaisAceitos());

        return repository.save(pontoExistente);
    }

    public void deletar(Long id) {
        PontoColeta ponto = buscarPorIdObrigatorio(id);
        repository.delete(ponto);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorEndereco(String endereco) {
        return repository.findByEnderecoContainingIgnoreCase(endereco);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorDataColeta(LocalDate data) {
        return repository.findByDiaColeta(data);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorMaterialAceito(String material) {
        return repository.findByMaterialAceito(material);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPontosColetaFutura() {
        return repository.findPontosColetaFutura(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return repository.findByPeriodoColeta(dataInicio, dataFim);
    }

    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return repository.existsById(id);
    }

    @Transactional(readOnly = true)
    public List<PontoColeta> buscarPorNomeLixoEletronico(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do lixo eletrônico é obrigatório");
        }
        return repository.findByLixoEletronicoNome(nome.trim());
    }
}