package com.example.ecoproject.repository;

import com.example.ecoproject.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT p FROM Projeto p JOIN p.organizacao o WHERE " +
            "(:regiao IS NULL OR :regiao = '' OR LOWER(p.regiao) LIKE LOWER(CONCAT('%', :regiao, '%'))) AND " +
            "(:nomeOrganizacao IS NULL OR :nomeOrganizacao = '' OR LOWER(o.nome) LIKE LOWER(CONCAT('%', :nomeOrganizacao, '%')))")
    List<Projeto> findByRegiaoAndNomeOrganizacao(
            @Param("regiao") String regiao,
            @Param("nomeOrganizacao") String nomeOrganizacao);
}