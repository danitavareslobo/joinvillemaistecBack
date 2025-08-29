package com.example.lixoeletronico.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "ponto_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 200, message = "Endereço deve ter no máximo 200 caracteres")
    @Column(name = "endereco", nullable = false, length = 200)
    private String endereco;

    @NotNull(message = "Data de coleta é obrigatória")
    @Column(name = "dia_coleta", nullable = false)
    private LocalDate diaColeta;

    @Size(max = 500, message = "Materiais aceitos deve ter no máximo 500 caracteres")
    @Column(name = "materiais_aceitos", length = 500)
    private String materiaisAceitos;

    @OneToMany(mappedBy = "pontoColeta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MaterialPontoColeta> materiais;

    public PontoColeta() {}

    public PontoColeta(String nome, String endereco, LocalDate diaColeta, String materiaisAceitos) {
        this.nome = nome;
        this.endereco = endereco;
        this.diaColeta = diaColeta;
        this.materiaisAceitos = materiaisAceitos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public LocalDate getDiaColeta() {
        return diaColeta;
    }

    public void setDiaColeta(LocalDate diaColeta) {
        this.diaColeta = diaColeta;
    }

    public String getMateriaisAceitos() {
        return materiaisAceitos;
    }

    public void setMateriaisAceitos(String materiaisAceitos) {
        this.materiaisAceitos = materiaisAceitos;
    }

    public List<MaterialPontoColeta> getMateriais() {
        return materiais;
    }

    public void setMateriais(List<MaterialPontoColeta> materiais) {
        this.materiais = materiais;
    }

    @Override
    public String toString() {
        return "PontoColeta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", diaColeta=" + diaColeta +
                ", materiaisAceitos='" + materiaisAceitos + '\'' +
                '}';
    }
}