package com.example.projetossustentaveis.repository;

import com.example.projetossustentaveis.entity.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {
}