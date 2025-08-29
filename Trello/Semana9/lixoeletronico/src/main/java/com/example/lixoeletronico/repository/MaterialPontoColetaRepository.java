package com.example.lixoeletronico.repository;

import com.example.lixoeletronico.entity.MaterialPontoColeta;
import com.example.lixoeletronico.entity.PontoColeta;
import com.example.lixoeletronico.entity.LixoEletronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialPontoColetaRepository extends JpaRepository<MaterialPontoColeta, Long> {

    List<MaterialPontoColeta> findByPontoColeta(PontoColeta pontoColeta);

    List<MaterialPontoColeta> findByLixoEletronico(LixoEletronico lixoEletronico);

    Optional<MaterialPontoColeta> findByPontoColetaAndLixoEletronico(PontoColeta pontoColeta, LixoEletronico lixoEletronico);

    boolean existsByPontoColetaAndLixoEletronico(PontoColeta pontoColeta, LixoEletronico lixoEletronico);

    List<MaterialPontoColeta> findByPontoColetaId(Long pontoColetaId);

    List<MaterialPontoColeta> findByLixoEletronicoId(Long lixoEletronicoId);

    @Query("SELECT COUNT(m) FROM MaterialPontoColeta m WHERE m.pontoColeta.id = :pontoId")
    Long countByPontoColetaId(@Param("pontoId") Long pontoId);
}