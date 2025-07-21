import models.ItemCardapio;
import models.Restaurante;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Restaurante restaurante;

    public static void main(String[] args) {
        System.out.print("Digite o nome do restaurante: ");
        String nomeRestaurante = scanner.nextLine();
        restaurante = new Restaurante(nomeRestaurante);

        System.out.println("\n=== BEM-VINDO AO SISTEMA DE PEDIDOS ===");
        System.out.println("Restaurante: " + nomeRestaurante);

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarItem();
                    break;
                case 2:
                    verCardapio();
                    break;
                case 3:
                    fazerPedido();
                    break;
                case 4:
                    verEstatisticas();
                    break;
                case 5:
                    System.out.println("Obrigado por usar o sistema! Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Cadastrar item");
        System.out.println("2. Ver cardápio");
        System.out.println("3. Fazer pedido");
        System.out.println("4. Ver estatísticas");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarItem() {
        System.out.println("\n=== CADASTRAR ITEM ===");

        System.out.print("Código do item: ");
        String codigo = scanner.nextLine();

        System.out.print("Nome do item: ");
        String nome = scanner.nextLine();

        System.out.print("Preço do item: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        if (preco <= 0) {
            System.out.println("Erro: Preço deve ser maior que zero!");
            return;
        }

        ItemCardapio novoItem = new ItemCardapio(codigo, nome, preco);
        restaurante.adicionarItem(novoItem);

        System.out.println("Item cadastrado com sucesso!");
    }

    private static void verCardapio() {
        System.out.println("\n=== VER CARDÁPIO ===");
        restaurante.listarCardapio();
    }

    private static void fazerPedido() {
        System.out.println("\n=== FAZER PEDIDO ===");

        System.out.print("Digite os códigos dos itens separados por espaço: ");
        String entrada = scanner.nextLine();
        String[] codigos = entrada.split(" ");

        List<ItemCardapio> itensEncontrados = restaurante.buscarItens(codigos);
        double valorTotal = restaurante.fazerPedido(codigos);

        System.out.println("\n=== RESUMO DO PEDIDO ===");

        if (itensEncontrados.isEmpty()) {
            System.out.println("Nenhum item encontrado!");
        } else {
            System.out.println("Itens do pedido:");
            for (ItemCardapio item : itensEncontrados) {
                System.out.println("- " + item.toString());
            }
        }

        System.out.printf("Valor total: R$ %.2f\n", valorTotal);

        if (codigos.length > itensEncontrados.size()) {
            System.out.println("Atenção: Alguns códigos não foram encontrados!");
        }
    }

    private static void verEstatisticas() {
        System.out.println("\n=== ESTATÍSTICAS ===");
        System.out.println("Total de restaurantes: " + Restaurante.getTotalRestaurantes());
        System.out.println("Total de itens cadastrados: " + Restaurante.getTotalItens());
        System.out.println("Nome do restaurante atual: " + restaurante.getNome());
    }
}