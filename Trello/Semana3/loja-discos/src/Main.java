import models.Genero;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Genero> generos = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA LOJA DE DISCOS ===");

        boolean continuar = true;
        while (continuar) {
            continuar = exibirMenuPrincipal();
        }

        scanner.close();
        System.out.println("Sistema encerrado!");
    }

    private static boolean exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("[1] Generos");
        System.out.println("[2] Discos");
        System.out.println("[3] Autores");
        System.out.println("[4] Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                menuGeneros();
                break;
            case 2:
                System.out.println("Funcionalidade de Discos será implementada no próximo exercício.");
                break;
            case 3:
                System.out.println("Funcionalidade de Autores será implementada no exercício 5.");
                break;
            case 4:
                return false;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
        return true;
    }

    private static void menuGeneros() {
        boolean voltarMenu = false;

        while (!voltarMenu) {
            System.out.println("\n=== MENU GÊNEROS ===");
            System.out.println("[1] Novo Genero");
            System.out.println("[2] Excluir Genero");
            System.out.println("[3] Listar Generos");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    novoGenero();
                    break;
                case 2:
                    excluirGenero();
                    break;
                case 3:
                    listarGeneros();
                    break;
                case 4:
                    voltarMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void novoGenero() {
        System.out.println("\n=== NOVO GÊNERO ===");

        System.out.print("Digite o nome do gênero: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome do gênero não pode estar vazio!");
            return;
        }

        for (Genero g : generos) {
            if (g.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Já existe um gênero com este nome!");
                return;
            }
        }

        System.out.print("Digite a descrição do gênero: ");
        String descricao = scanner.nextLine();

        if (descricao.trim().isEmpty()) {
            System.out.println("Descrição do gênero não pode estar vazia!");
            return;
        }

        Genero novoGenero = new Genero(nome, descricao);
        generos.add(novoGenero);

        System.out.println("Gênero criado com sucesso!");
        System.out.println(novoGenero.getInfo());
    }

    private static void excluirGenero() {
        System.out.println("\n=== EXCLUIR GÊNERO ===");

        if (generos.isEmpty()) {
            System.out.println("Não há gêneros cadastrados!");
            return;
        }

        System.out.println("Gêneros disponíveis:");
        for (int i = 0; i < generos.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + generos.get(i).getNome());
        }

        System.out.print("Digite o número do gênero a ser excluído (0 para cancelar): ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (indice < 1 || indice > generos.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Genero generoRemovido = generos.remove(indice - 1);
        System.out.println("Gênero '" + generoRemovido.getNome() + "' excluído com sucesso!");
    }

    private static void listarGeneros() {
        System.out.println("\n=== LISTA DE GÊNEROS ===");

        if (generos.isEmpty()) {
            System.out.println("Não há gêneros cadastrados!");
            return;
        }

        for (int i = 0; i < generos.size(); i++) {
            System.out.println("\n--- Gênero " + (i + 1) + " ---");
            System.out.println(generos.get(i).getInfo());
        }

        System.out.println("\nTotal de gêneros: " + generos.size());
    }
}