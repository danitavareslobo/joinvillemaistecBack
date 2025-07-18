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
                    System.out.println("Opção Venda selecionada.");
                    break;
                case 2:
                    System.out.println("Opção Reposição selecionada.");
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