package com.example.lixoeletronico.repository;

import com.example.lixoeletronico.entity.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta, Long> {

    @Query("SELECT p FROM PontoColeta p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<PontoColeta> findByNomeContainingIgnoreCase(@Param("nome") String nome);

    Optional<PontoColeta> findByNomeIgnoreCase(String nome);

    @Query("SELECT p FROM PontoColeta p WHERE LOWER(p.endereco) LIKE LOWER(CONCAT('%', :endereco, '%'))")
    List<PontoColeta> findByEnderecoContainingIgnoreCase(@Param("endereco") String endereco);

    List<PontoColeta> findByDiaColeta(LocalDate diaColeta);

    @Query("SELECT p FROM PontoColeta p WHERE p.diaColeta BETWEEN :dataInicio AND :dataFim ORDER BY p.diaColeta ASC")
    List<PontoColeta> findByPeriodoColeta(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT p FROM PontoColeta p WHERE LOWER(p.materiaisAceitos) LIKE LOWER(CONCAT('%', :material, '%'))")
    List<PontoColeta> findByMaterialAceito(@Param("material") String material);

    boolean existsByNomeIgnoreCase(String nome);

    @Query("SELECT p FROM PontoColeta p ORDER BY p.nome ASC")
    List<PontoColeta> findAllOrderByNome();

    @Query("SELECT p FROM PontoColeta p WHERE p.diaColeta >= :hoje ORDER BY p.diaColeta ASC")
    List<PontoColeta> findPontosColetaFutura(@Param("hoje") LocalDate hoje);
}