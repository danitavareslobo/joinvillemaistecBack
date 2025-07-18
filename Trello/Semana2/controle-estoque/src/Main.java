import java.util.Scanner;

public class Main {
    private static String nomeProduto1 = "Tomates";
    private static double precoProduto1 = 1.00;
    private static int quantidadeProduto1 = 10;

    private static String nomeProduto2 = "Batatas";
    private static double precoProduto2 = 2.00;
    private static int quantidadeProduto2 = 5;

    private static String nomeProduto3 = "Cebolas";
    private static double precoProduto3 = 1.50;
    private static int quantidadeProduto3 = 8;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CONTROLE DE ESTOQUE ===");

        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    realizarReposicao();
                    break;
                case 3:
                    System.out.println("Opção Relatório selecionada.");
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("[1] Venda");
        System.out.println("[2] Reposição");
        System.out.println("[3] Relatório");
        System.out.println("[4] Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirProdutos() {
        System.out.println("Produtos disponíveis:");
        System.out.println("[1] " + nomeProduto1 + " - Estoque: " + quantidadeProduto1);
        System.out.println("[2] " + nomeProduto2 + " - Estoque: " + quantidadeProduto2);
        System.out.println("[3] " + nomeProduto3 + " - Estoque: " + quantidadeProduto3);
    }

    private static void realizarVenda() {
        System.out.println("\n=== VENDA ===");
        exibirProdutos();
        System.out.print("Qual produto deseja vender? ");

        int produtoVenda = scanner.nextInt();
        System.out.print("Quantidade: ");
        int quantidadeVenda = scanner.nextInt();

        if (quantidadeVenda <= 0) {
            System.out.println("Quantidade inválida! Deve ser maior que zero.");
            return;
        }

        switch (produtoVenda) {
            case 1:
                if (quantidadeVenda > quantidadeProduto1) {
                    System.out.println("Estoque insuficiente! Disponível: " + quantidadeProduto1);
                } else {
                    quantidadeProduto1 -= quantidadeVenda;
                    System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto1 + " vendidos.");
                }
                break;
            case 2:
                if (quantidadeVenda > quantidadeProduto2) {
                    System.out.println("Estoque insuficiente! Disponível: " + quantidadeProduto2);
                } else {
                    quantidadeProduto2 -= quantidadeVenda;
                    System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto2 + " vendidos.");
                }
                break;
            case 3:
                if (quantidadeVenda > quantidadeProduto3) {
                    System.out.println("Estoque insuficiente! Disponível: " + quantidadeProduto3);
                } else {
                    quantidadeProduto3 -= quantidadeVenda;
                    System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto3 + " vendidos.");
                }
                break;
            default:
                System.out.println("Produto inválido!");
        }
    }

    private static void realizarReposicao() {
        System.out.println("\n=== REPOSIÇÃO ===");
        exibirProdutos();
        System.out.print("Qual produto deseja repor? ");

        int produtoReposicao = scanner.nextInt();
        System.out.print("Quantidade: ");
        int quantidadeReposicao = scanner.nextInt();

        if (quantidadeReposicao <= 0) {
            System.out.println("Quantidade inválida! Deve ser maior que zero.");
            return;
        }

        switch (produtoReposicao) {
            case 1:
                quantidadeProduto1 += quantidadeReposicao;
                System.out.println("Reposição realizada com sucesso! " + quantidadeReposicao + " " + nomeProduto1 + " adicionados ao estoque.");
                break;
            case 2:
                quantidadeProduto2 += quantidadeReposicao;
                System.out.println("Reposição realizada com sucesso! " + quantidadeReposicao + " " + nomeProduto2 + " adicionados ao estoque.");
                break;
            case 3:
                quantidadeProduto3 += quantidadeReposicao;
                System.out.println("Reposição realizada com sucesso! " + quantidadeReposicao + " " + nomeProduto3 + " adicionados ao estoque.");
                break;
            default:
                System.out.println("Produto inválido!");
        }
    }
}