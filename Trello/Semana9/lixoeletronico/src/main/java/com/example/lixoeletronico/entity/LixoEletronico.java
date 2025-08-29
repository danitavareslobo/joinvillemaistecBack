package com.example.lixoeletronico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "lixo_eletronico")
public class LixoEletronico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tipo do lixo é obrigatório")
    @Size(max = 100, message = "Tipo do lixo deve ter no máximo 100 caracteres")
    @Column(name = "tipo_lixo", nullable = false, length = 100)
    private String tipoLixo;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    @Column(name = "descricao", length = 500)
    private String descricao;

    public LixoEletronico() {}

    public LixoEletronico(String tipoLixo, String descricao) {
        this.tipoLixo = tipoLixo;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoLixo() {
        return tipoLixo;
    }

    public void setTipoLixo(String tipoLixo) {
        this.tipoLixo = tipoLixo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "LixoEletronico{" +
                "id=" + id +
                ", tipoLixo='" + tipoLixo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}