package com.example.lixoeletronico.repository;

import com.example.lixoeletronico.entity.LixoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LixoEletronicoRepository extends JpaRepository<LixoEletronico, Long> {

    @Query("SELECT l FROM LixoEletronico l WHERE LOWER(l.tipoLixo) LIKE LOWER(CONCAT('%', :tipo, '%'))")
    List<LixoEletronico> findByTipoLixoContainingIgnoreCase(@Param("tipo") String tipo);

    Optional<LixoEletronico> findByTipoLixoIgnoreCase(String tipoLixo);

    boolean existsByTipoLixoIgnoreCase(String tipoLixo);

    @Query("SELECT l FROM LixoEletronico l ORDER BY l.tipoLixo ASC")
    List<LixoEletronico> findAllOrderByTipo();
}