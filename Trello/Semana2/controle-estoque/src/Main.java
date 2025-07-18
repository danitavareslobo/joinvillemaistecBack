import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nomeProduto1 = "Tomates";
        double precoProduto1 = 1.00;
        int quantidadeProduto1 = 10;

        String nomeProduto2 = "Batatas";
        double precoProduto2 = 2.00;
        int quantidadeProduto2 = 5;

        String nomeProduto3 = "Cebolas";
        double precoProduto3 = 1.50;
        int quantidadeProduto3 = 8;

        System.out.println("=== SISTEMA DE CONTROLE DE ESTOQUE ===");

        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("[1] Venda");
            System.out.println("[2] Reposição");
            System.out.println("[3] Relatório");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n=== VENDA ===");
                    System.out.println("Produtos disponíveis:");
                    System.out.println("[1] " + nomeProduto1 + " - Estoque: " + quantidadeProduto1);
                    System.out.println("[2] " + nomeProduto2 + " - Estoque: " + quantidadeProduto2);
                    System.out.println("[3] " + nomeProduto3 + " - Estoque: " + quantidadeProduto3);
                    System.out.print("Qual produto deseja vender? ");

                    int produtoVenda = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidadeVenda = scanner.nextInt();

                    switch (produtoVenda) {
                        case 1:
                            quantidadeProduto1 -= quantidadeVenda;
                            System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto1 + " vendidos.");
                            break;
                        case 2:
                            quantidadeProduto2 -= quantidadeVenda;
                            System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto2 + " vendidos.");
                            break;
                        case 3:
                            quantidadeProduto3 -= quantidadeVenda;
                            System.out.println("Venda realizada com sucesso! " + quantidadeVenda + " " + nomeProduto3 + " vendidos.");
                            break;
                        default:
                            System.out.println("Produto inválido!");
                    }
                    break;
                case 2:
                    System.out.println("\n=== REPOSIÇÃO ===");
                    System.out.println("Produtos disponíveis:");
                    System.out.println("[1] " + nomeProduto1 + " - Estoque: " + quantidadeProduto1);
                    System.out.println("[2] " + nomeProduto2 + " - Estoque: " + quantidadeProduto2);
                    System.out.println("[3] " + nomeProduto3 + " - Estoque: " + quantidadeProduto3);
                    System.out.print("Qual produto deseja repor? ");

                    int produtoReposicao = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidadeReposicao = scanner.nextInt();

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
}