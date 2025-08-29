package com.example.lixoeletronico.service;

import com.example.lixoeletronico.entity.LixoEletronico;
import com.example.lixoeletronico.repository.LixoEletronicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LixoEletronicoService {

    @Autowired
    private LixoEletronicoRepository repository;

    public LixoEletronico criar(LixoEletronico lixoEletronico) {
        // Validar se já existe o mesmo tipo
        if (repository.existsByTipoLixoIgnoreCase(lixoEletronico.getTipoLixo())) {
            throw new IllegalArgumentException("Já existe um lixo eletrônico com este tipo: " + lixoEletronico.getTipoLixo());
        }
        return repository.save(lixoEletronico);
    }

    @Transactional(readOnly = true)
    public List<LixoEletronico> buscarTodos() {
        return repository.findAllOrderByTipo();
    }

    @Transactional(readOnly = true)
    public Optional<LixoEletronico> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public LixoEletronico buscarPorIdObrigatorio(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Lixo eletrônico não encontrado com ID: " + id));
    }

    public LixoEletronico atualizar(Long id, LixoEletronico lixoAtualizado) {
        LixoEletronico lixoExistente = buscarPorIdObrigatorio(id);

        // Validar se o novo tipo já existe em outro registro
        if (!lixoExistente.getTipoLixo().equalsIgnoreCase(lixoAtualizado.getTipoLixo()) &&
                repository.existsByTipoLixoIgnoreCase(lixoAtualizado.getTipoLixo())) {
            throw new IllegalArgumentException("Já existe um lixo eletrônico com este tipo: " + lixoAtualizado.getTipoLixo());
        }

        lixoExistente.setTipoLixo(lixoAtualizado.getTipoLixo());
        lixoExistente.setDescricao(lixoAtualizado.getDescricao());

        return repository.save(lixoExistente);
    }

    public void deletar(Long id) {
        LixoEletronico lixo = buscarPorIdObrigatorio(id);
        repository.delete(lixo);
    }

    @Transactional(readOnly = true)
    public List<LixoEletronico> buscarPorTipo(String tipo) {
        return repository.findByTipoLixoContainingIgnoreCase(tipo);
    }

    @Transactional(readOnly = true)
    public boolean existe(Long id) {
        return repository.existsById(id);
    }
}