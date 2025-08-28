package com.example.coletaseletiva.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rotas")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "area_abrangencia", nullable = false)
    private String areaAbrangencia;

    @Column(name = "percentual_eficiencia")
    private Double percentualEficiencia;

    public Rota() {}

    public Rota(String descricao, String areaAbrangencia, Double percentualEficiencia) {
        this.descricao = descricao;
        this.areaAbrangencia = areaAbrangencia;
        this.percentualEficiencia = percentualEficiencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAreaAbrangencia() {
        return areaAbrangencia;
    }

    public void setAreaAbrangencia(String areaAbrangencia) {
        this.areaAbrangencia = areaAbrangencia;
    }

    public Double getPercentualEficiencia() {
        return percentualEficiencia;
    }

    public void setPercentualEficiencia(Double percentualEficiencia) {
        this.percentualEficiencia = percentualEficiencia;
    }
}