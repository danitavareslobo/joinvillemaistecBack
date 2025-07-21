package models;

public class Disco {
    private String titulo;
    private int anoLancamento;
    private double preco;
    private boolean ativo;
    private Genero genero;

    public Disco(String titulo, int anoLancamento, double preco, Genero genero) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.preco = preco;
        this.genero = genero;
        this.ativo = true;
    }

    public String getInfo() {
        String status = ativo ? "Ativo" : "Inativo";
        return "=== INFORMAÇÕES DO DISCO ===\n" +
                "Título: " + titulo + "\n" +
                "Ano de Lançamento: " + anoLancamento + "\n" +
                "Preço: R$ " + String.format("%.2f", preco) + "\n" +
                "Status: " + status + "\n" +
                "=== GÊNERO ===\n" +
                genero.getInfo();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}