package models;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private String nome;
    private List<ItemCardapio> cardapio;

    private static int totalItens = 0;
    private static int totalRestaurantes = 0;

    public Restaurante(String nome) {
        this.nome = nome;
        this.cardapio = new ArrayList<>();
        totalRestaurantes++;
    }

    public void adicionarItem(ItemCardapio item) {
        cardapio.add(item);
        totalItens++;
    }

    public void listarCardapio() {
        if (cardapio.isEmpty()) {
            System.out.println("Cardápio vazio!");
            return;
        }

        System.out.println("\n=== CARDÁPIO - " + nome.toUpperCase() + " ===");
        for (ItemCardapio item : cardapio) {
            System.out.println(item.toString());
        }
        System.out.println("================================");
    }

    public String getNome() {
        return nome;
    }

    public static int getTotalItens() {
        return totalItens;
    }

    public static int getTotalRestaurantes() {
        return totalRestaurantes;
    }

    public List<ItemCardapio> buscarItens(String... codigos) {
        List<ItemCardapio> itensEncontrados = new ArrayList<>();

        for (String codigo : codigos) {
            for (ItemCardapio item : cardapio) {
                if (item.getCodigo().equals(codigo)) {
                    itensEncontrados.add(item);
                    break;
                }
            }
        }

        return itensEncontrados;
    }

    public double fazerPedido(String... codigos) {
        double valorTotal = 0.0;

        for (String codigo : codigos) {
            boolean encontrado = false;
            for (ItemCardapio item : cardapio) {
                if (item.getCodigo().equals(codigo)) {
                    valorTotal += item.getPreco();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                valorTotal += 0.0;
            }
        }

        return valorTotal;
    }
}