package com.example.coletaseletiva.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contratos")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "coletor_id", nullable = false)
    private Long coletorId;

    @Column(name = "rota_id", nullable = false)
    private Long rotaId;

    public Contrato() {}

    public Contrato(Long coletorId, Long rotaId) {
        this.coletorId = coletorId;
        this.rotaId = rotaId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getColetorId() {
        return coletorId;
    }

    public void setColetorId(Long coletorId) {
        this.coletorId = coletorId;
    }

    public Long getRotaId() {
        return rotaId;
    }

    public void setRotaId(Long rotaId) {
        this.rotaId = rotaId;
    }
}