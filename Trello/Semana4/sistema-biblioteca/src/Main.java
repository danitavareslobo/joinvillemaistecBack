import models.Biblioteca;
import models.Livro;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Biblioteca biblioteca;

    public static void main(String[] args) {
        System.out.print("Digite o nome da biblioteca: ");
        String nomeBiblioteca = scanner.nextLine();
        biblioteca = new Biblioteca(nomeBiblioteca);

        System.out.println("\n=== SISTEMA DE GESTÃO DE BIBLIOTECA ===");
        System.out.println("Biblioteca: " + nomeBiblioteca);

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    menuGestaoLivros();
                    break;
                case 2:
                    menuGestaoEmprestimos();
                    break;
                case 3:
                    menuRelatoriosConsultas();
                    break;
                case 4:
                    menuAdministracao();
                    break;
                case 5:
                    System.out.println("Obrigado por usar o Sistema de Biblioteca! Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gestão de Livros");
        System.out.println("2. Gestão de Empréstimos");
        System.out.println("3. Relatórios e Consultas");
        System.out.println("4. Administração do Sistema");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuGestaoLivros() {
        int opcao;
        do {
            System.out.println("\n=== GESTÃO DE LIVROS ===");
            System.out.println("1. Cadastrar Novo Livro");
            System.out.println("2. Editar Informações de Livro");
            System.out.println("3. Remover Livro do Acervo");
            System.out.println("4. Listar Todos os Livros");
            System.out.println("5. Buscar Livro por ISBN");
            System.out.println("6. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    cadastrarNovoLivro();
                    break;
                case 2:
                    editarLivro();
                    break;
                case 3:
                    removerLivro();
                    break;
                case 4:
                    biblioteca.listarTodosLivros();
                    break;
                case 5:
                    buscarLivroPorIsbn();
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);
    }

    private static void menuGestaoEmprestimos() {
        int opcao;
        do {
            System.out.println("\n=== GESTÃO DE EMPRÉSTIMOS ===");
            System.out.println("1. Registrar Empréstimo");
            System.out.println("2. Registrar Devolução");
            System.out.println("3. Listar Livros Emprestados");
            System.out.println("4. Listar Livros Disponíveis");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    registrarEmprestimo();
                    break;
                case 2:
                    registrarDevolucao();
                    break;
                case 3:
                    biblioteca.listarLivrosEmprestados();
                    break;
                case 4:
                    biblioteca.listarLivrosDisponiveis();
                    break;
                case 5:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 5);
    }

    private static void menuRelatoriosConsultas() {
        int opcao;
        do {
            System.out.println("\n=== RELATÓRIOS E CONSULTAS ===");
            System.out.println("1. Relatório Completo do Acervo");
            System.out.println("2. Buscar Livros por Autores");
            System.out.println("3. Estatísticas de Empréstimos");
            System.out.println("4. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    biblioteca.listarTodosLivros();
                    break;
                case 2:
                    buscarLivrosPorAutor();
                    break;
                case 3:
                    biblioteca.exibirEstatisticas();
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void menuAdministracao() {
        int opcao;
        do {
            System.out.println("\n=== ADMINISTRAÇÃO DO SISTEMA ===");
            System.out.println("1. Total de Livros Cadastrados");
            System.out.println("2. Total de Bibliotecas");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    System.out.println("Total de livros cadastrados no sistema: " + Livro.getTotalLivros());
                    System.out.println("Total de livros nesta biblioteca: " + biblioteca.getTotalLivrosAcervo());
                    break;
                case 2:
                    System.out.println("Total de bibliotecas criadas: " + Biblioteca.getTotalBibliotecas());
                    break;
                case 3:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 3);
    }

    private static int lerOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    private static void cadastrarNovoLivro() {
        System.out.println("\n=== CADASTRAR NOVO LIVRO ===");
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Ano de Publicação: ");
        int ano = scanner.nextInt();
        scanner.nextLine();

        Livro novoLivro = new Livro(isbn, titulo, autor, ano);
        biblioteca.cadastrarLivro(novoLivro);
    }

    private static void editarLivro() {
        System.out.println("\n=== EDITAR LIVRO ===");
        System.out.print("Digite o ISBN do livro a ser editado: ");
        String isbn = scanner.nextLine();

        Livro livro = biblioteca.buscarLivroPorIsbn(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado!");
            return;
        }

        System.out.println("Livro encontrado: " + livro.toString());
        System.out.print("Novo título: ");
        String novoTitulo = scanner.nextLine();

        System.out.print("Novo autor: ");
        String novoAutor = scanner.nextLine();

        System.out.print("Novo ano: ");
        int novoAno = scanner.nextInt();
        scanner.nextLine();

        if (biblioteca.editarLivro(isbn, novoTitulo, novoAutor, novoAno)) {
            System.out.println("Livro editado com sucesso!");
        } else {
            System.out.println("Erro ao editar livro!");
        }
    }

    private static void removerLivro() {
        System.out.println("\n=== REMOVER LIVRO ===");
        System.out.print("Digite o ISBN do livro a ser removido: ");
        String isbn = scanner.nextLine();

        if (biblioteca.removerLivro(isbn)) {
            System.out.println("Livro removido com sucesso!");
        } else {
            System.out.println("Erro ao remover livro!");
        }
    }

    private static void buscarLivroPorIsbn() {
        System.out.println("\n=== BUSCAR LIVRO POR ISBN ===");
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();

        Livro livro = biblioteca.buscarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println("Livro encontrado:");
            System.out.println(livro.toString());
        } else {
            System.out.println("Livro não encontrado!");
        }
    }

    private static void buscarLivrosPorAutor() {
        System.out.println("\n=== BUSCAR LIVROS POR AUTOR ===");
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();

        List<Livro> livrosEncontrados = biblioteca.buscarLivrosPorAutor(autor);
        if (livrosEncontrados.isEmpty()) {
            System.out.println("Nenhum livro encontrado para este autor!");
        } else {
            System.out.println("Livros encontrados:");
            for (Livro livro : livrosEncontrados) {
                System.out.println(livro.toString());
            }
        }
    }

    private static void registrarEmprestimo() {
        System.out.println("\n=== REGISTRAR EMPRÉSTIMO ===");
        System.out.print("ISBN do livro: ");
        String isbn = scanner.nextLine();

        System.out.print("Nome do usuário: ");
        String nomeUsuario = scanner.nextLine();

        biblioteca.registrarEmprestimo(isbn, nomeUsuario);
    }

    private static void registrarDevolucao() {
        System.out.println("\n=== REGISTRAR DEVOLUÇÃO ===");
        System.out.print("ISBN do livro a ser devolvido: ");
        String isbn = scanner.nextLine();

        biblioteca.registrarDevolucao(isbn);
    }
}