package models;

public class Autor {
    private String nome;
    private String nacionalidade;
    private Disco disco;

    public Autor(String nome, String nacionalidade, Disco disco) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.disco = disco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }
}