package com.example.ecoproject.repository;

import com.example.ecoproject.entity.Organizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrganizacaoRepository extends JpaRepository<Organizacao, Long> {

    @Query("SELECT o FROM Organizacao o WHERE " +
            "(:nome IS NULL OR :nome = '' OR LOWER(o.nome) LIKE LOWER(CONCAT('%', :nome, '%'))) AND " +
            "(:contato IS NULL OR :contato = '' OR LOWER(o.contato) LIKE LOWER(CONCAT('%', :contato, '%')))")
    List<Organizacao> findByNomeAndContatoContaining(
            @Param("nome") String nome,
            @Param("contato") String contato);
}