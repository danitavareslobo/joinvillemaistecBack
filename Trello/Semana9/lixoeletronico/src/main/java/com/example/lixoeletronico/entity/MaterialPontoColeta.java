package com.example.lixoeletronico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "material_ponto_coleta")
public class MaterialPontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ponto_coleta_id", nullable = false)
    @NotNull(message = "Ponto de coleta é obrigatório")
    private PontoColeta pontoColeta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lixo_eletronico_id", nullable = false)
    @NotNull(message = "Lixo eletrônico é obrigatório")
    private LixoEletronico lixoEletronico;

    @Positive(message = "Capacidade máxima deve ser positiva")
    @Column(name = "capacidade_maxima")
    private Integer capacidadeMaxima;

    public MaterialPontoColeta() {}

    public MaterialPontoColeta(PontoColeta pontoColeta, LixoEletronico lixoEletronico, Integer capacidadeMaxima) {
        this.pontoColeta = pontoColeta;
        this.lixoEletronico = lixoEletronico;
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PontoColeta getPontoColeta() {
        return pontoColeta;
    }

    public void setPontoColeta(PontoColeta pontoColeta) {
        this.pontoColeta = pontoColeta;
    }

    public LixoEletronico getLixoEletronico() {
        return lixoEletronico;
    }

    public void setLixoEletronico(LixoEletronico lixoEletronico) {
        this.lixoEletronico = lixoEletronico;
    }

    public Integer getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(Integer capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    @Override
    public String toString() {
        return "MaterialPontoColeta{" +
                "id=" + id +
                ", capacidadeMaxima=" + capacidadeMaxima +
                '}';
    }
}