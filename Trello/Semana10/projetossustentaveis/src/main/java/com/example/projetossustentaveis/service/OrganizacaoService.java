package com.example.projetossustentaveis.service;

import com.example.projetossustentaveis.entity.Organizacao;
import com.example.projetossustentaveis.exception.ResourceNotFoundException;
import com.example.projetossustentaveis.repository.OrganizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizacaoService {

    @Autowired
    private OrganizacaoRepository organizacaoRepository;

    public List<Organizacao> findAll() {
        return organizacaoRepository.findAll();
    }

    public Organizacao findById(Long id) {
        return organizacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organização não encontrada com ID: " + id));
    }

    public Organizacao save(Organizacao organizacao) {
        return organizacaoRepository.save(organizacao);
    }

    public Organizacao update(Long id, Organizacao organizacaoAtualizada) {
        Organizacao organizacao = findById(id);

        organizacao.setNome(organizacaoAtualizada.getNome());
        organizacao.setContato(organizacaoAtualizada.getContato());

        return organizacaoRepository.save(organizacao);
    }

    public void delete(Long id) {
        Organizacao organizacao = findById(id);
        organizacaoRepository.delete(organizacao);
    }
}