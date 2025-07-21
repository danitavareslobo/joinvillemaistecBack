package models;

public class ItemCardapio {
    private String codigo;
    private String nome;
    private double preco;

    public ItemCardapio(String codigo, String nome, double preco) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        } else {
            System.out.println("Preço deve ser maior que zero!");
        }
    }

    @Override
    public String toString() {
        return String.format("Código: %s | Nome: %s | Preço: R$ %.2f",
                codigo, nome, preco);
    }
}