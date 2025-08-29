package com.example.ecoproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "projetos")
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column
    private String regiao;

    @Column(name = "estimativa_reducao_co2")
    private Double estimativaReducaoCO2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizacao_id", nullable = false)
    @NotNull(message = "Organização é obrigatória")
    private Organizacao organizacao;

    // Construtores
    public Projeto() {}

    public Projeto(String nome, String descricao, String regiao,
                   Double estimativaReducaoCO2, Organizacao organizacao) {
        this.nome = nome;
        this.descricao = descricao;
        this.regiao = regiao;
        this.estimativaReducaoCO2 = estimativaReducaoCO2;
        this.organizacao = organizacao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getRegiao() { return regiao; }
    public void setRegiao(String regiao) { this.regiao = regiao; }

    public Double getEstimativaReducaoCO2() { return estimativaReducaoCO2; }
    public void setEstimativaReducaoCO2(Double estimativaReducaoCO2) {
        this.estimativaReducaoCO2 = estimativaReducaoCO2;
    }

    public Organizacao getOrganizacao() { return organizacao; }
    public void setOrganizacao(Organizacao organizacao) { this.organizacao = organizacao; }
}