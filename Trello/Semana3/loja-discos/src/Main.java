import models.Genero;
import models.Disco;
import models.Autor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Genero> generos = new ArrayList<>();
    private static List<Disco> discos = new ArrayList<>();
    private static List<Autor> autores = new ArrayList<>();

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
                menuDiscos();
                break;
            case 3:
                menuAutores();
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

    private static void menuDiscos() {
        boolean voltarMenu = false;

        while (!voltarMenu) {
            System.out.println("\n=== MENU DISCOS ===");
            System.out.println("[1] Novo Disco");
            System.out.println("[2] Inativar Disco");
            System.out.println("[3] Listar Discos");
            System.out.println("[4] Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    novoDisco();
                    break;
                case 2:
                    inativarDisco();
                    break;
                case 3:
                    listarDiscos();
                    break;
                case 4:
                    voltarMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void novoDisco() {
        System.out.println("\n=== NOVO DISCO ===");

        if (generos.isEmpty()) {
            System.out.println("É necessário ter pelo menos um gênero cadastrado para criar um disco!");
            return;
        }

        System.out.print("Digite o título do disco: ");
        String titulo = scanner.nextLine();

        if (titulo.trim().isEmpty()) {
            System.out.println("Título do disco não pode estar vazio!");
            return;
        }

        for (Disco d : discos) {
            if (d.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Já existe um disco com este título!");
                return;
            }
        }

        System.out.print("Digite o ano de lançamento: ");
        int anoLancamento = scanner.nextInt();

        if (anoLancamento < 1900 || anoLancamento > 2025) {
            System.out.println("Ano de lançamento inválido!");
            scanner.nextLine();
            return;
        }

        System.out.print("Digite o preço: R$ ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        if (preco <= 0) {
            System.out.println("Preço deve ser maior que zero!");
            return;
        }

        System.out.println("\nGêneros disponíveis:");
        for (int i = 0; i < generos.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + generos.get(i).getNome());
        }

        System.out.print("Escolha o gênero (número): ");
        int indiceGenero = scanner.nextInt();
        scanner.nextLine();

        if (indiceGenero < 1 || indiceGenero > generos.size()) {
            System.out.println("Gênero inválido!");
            return;
        }

        Genero generoSelecionado = generos.get(indiceGenero - 1);
        Disco novoDisco = new Disco(titulo, anoLancamento, preco, generoSelecionado);
        discos.add(novoDisco);

        System.out.println("Disco criado com sucesso!");
        System.out.println(novoDisco.getInfo());
    }

    private static void inativarDisco() {
        System.out.println("\n=== INATIVAR/ATIVAR DISCO ===");

        if (discos.isEmpty()) {
            System.out.println("Não há discos cadastrados!");
            return;
        }

        System.out.println("Discos disponíveis:");
        for (int i = 0; i < discos.size(); i++) {
            String status = discos.get(i).isAtivo() ? "Ativo" : "Inativo";
            System.out.println("[" + (i + 1) + "] " + discos.get(i).getTitulo() + " - " + status);
        }

        System.out.print("Digite o número do disco (0 para cancelar): ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice == 0) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (indice < 1 || indice > discos.size()) {
            System.out.println("Índice inválido!");
            return;
        }

        Disco disco = discos.get(indice - 1);
        boolean novoStatus = !disco.isAtivo();
        disco.setAtivo(novoStatus);

        String acao = novoStatus ? "ativado" : "inativado";
        System.out.println("Disco '" + disco.getTitulo() + "' " + acao + " com sucesso!");
    }

    private static void listarDiscos() {
        System.out.println("\n=== LISTA DE DISCOS ===");

        if (discos.isEmpty()) {
            System.out.println("Não há discos cadastrados!");
            return;
        }

        System.out.println("Filtrar por:");
        System.out.println("[1] Todos os discos");
        System.out.println("[2] Apenas discos ativos");
        System.out.println("[3] Apenas discos inativos");
        System.out.print("Escolha uma opção: ");

        int filtro = scanner.nextInt();
        scanner.nextLine();

        List<Disco> discosParaExibir = new ArrayList<>();

        switch (filtro) {
            case 1:
                discosParaExibir.addAll(discos);
                break;
            case 2:
                for (Disco d : discos) {
                    if (d.isAtivo()) {
                        discosParaExibir.add(d);
                    }
                }
                break;
            case 3:
                for (Disco d : discos) {
                    if (!d.isAtivo()) {
                        discosParaExibir.add(d);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (discosParaExibir.isEmpty()) {
            System.out.println("Nenhum disco encontrado com o filtro selecionado!");
            return;
        }

        for (int i = 0; i < discosParaExibir.size(); i++) {
            System.out.println("\n--- Disco " + (i + 1) + " ---");
            System.out.println(discosParaExibir.get(i).getInfo());
        }

        System.out.println("\nTotal de discos exibidos: " + discosParaExibir.size());
        System.out.println("Total geral de discos: " + discos.size());
    }

    private static void menuAutores() {
        boolean voltarMenu = false;

        while (!voltarMenu) {
            System.out.println("\n=== MENU AUTORES ===");
            System.out.println("[1] Novo Autor");
            System.out.println("[2] Listar Autores");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    novoAutor();
                    break;
                case 2:
                    listarAutores();
                    break;
                case 3:
                    voltarMenu = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void novoAutor() {
        System.out.println("\n=== NOVO AUTOR ===");

        if (discos.isEmpty()) {
            System.out.println("É necessário ter pelo menos um disco cadastrado para criar um autor!");
            return;
        }

        List<Disco> discosAtivos = new ArrayList<>();
        for (Disco d : discos) {
            if (d.isAtivo()) {
                discosAtivos.add(d);
            }
        }

        if (discosAtivos.isEmpty()) {
            System.out.println("É necessário ter pelo menos um disco ativo para criar um autor!");
            return;
        }

        System.out.print("Digite o nome do autor: ");
        String nome = scanner.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("Nome do autor não pode estar vazio!");
            return;
        }

        for (Autor a : autores) {
            if (a.getNome().equalsIgnoreCase(nome)) {
                System.out.println("Já existe um autor com este nome!");
                return;
            }
        }

        System.out.print("Digite a nacionalidade do autor: ");
        String nacionalidade = scanner.nextLine();

        if (nacionalidade.trim().isEmpty()) {
            System.out.println("Nacionalidade do autor não pode estar vazia!");
            return;
        }

        System.out.println("\nDiscos ativos disponíveis:");
        for (int i = 0; i < discosAtivos.size(); i++) {
            Disco disco = discosAtivos.get(i);
            System.out.println("[" + (i + 1) + "] " + disco.getTitulo() +
                    " (" + disco.getAnoLancamento() + ") - " +
                    disco.getGenero().getNome());
        }

        System.out.print("Escolha o disco (número): ");
        int indiceDisco = scanner.nextInt();
        scanner.nextLine();

        if (indiceDisco < 1 || indiceDisco > discosAtivos.size()) {
            System.out.println("Disco inválido!");
            return;
        }

        Disco discoSelecionado = discosAtivos.get(indiceDisco - 1);

        for (Autor a : autores) {
            if (a.getDisco().getTitulo().equals(discoSelecionado.getTitulo())) {
                System.out.println("Este disco já possui um autor associado: " + a.getNome());
                System.out.print("Deseja continuar mesmo assim? (s/n): ");
                String confirmacao = scanner.nextLine();
                if (!confirmacao.equalsIgnoreCase("s")) {
                    System.out.println("Operação cancelada.");
                    return;
                }
                break;
            }
        }

        Autor novoAutor = new Autor(nome, nacionalidade, discoSelecionado);
        autores.add(novoAutor);

        System.out.println("Autor criado com sucesso!");
        System.out.println(novoAutor.getInfo());
    }

    private static void listarAutores() {
        System.out.println("\n=== LISTA DE AUTORES ===");

        if (autores.isEmpty()) {
            System.out.println("Não há autores cadastrados!");
            return;
        }

        System.out.println("Filtrar por:");
        System.out.println("[1] Todos os autores");
        System.out.println("[2] Autores com discos ativos");
        System.out.println("[3] Autores com discos inativos");
        System.out.println("[4] Buscar por nacionalidade");
        System.out.print("Escolha uma opção: ");

        int filtro = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresParaExibir = new ArrayList<>();

        switch (filtro) {
            case 1:
                autoresParaExibir.addAll(autores);
                break;
            case 2:
                for (Autor a : autores) {
                    if (a.getDisco().isAtivo()) {
                        autoresParaExibir.add(a);
                    }
                }
                break;
            case 3:
                for (Autor a : autores) {
                    if (!a.getDisco().isAtivo()) {
                        autoresParaExibir.add(a);
                    }
                }
                break;
            case 4:
                System.out.print("Digite a nacionalidade para buscar: ");
                String nacionalidadeBusca = scanner.nextLine();
                for (Autor a : autores) {
                    if (a.getNacionalidade().toLowerCase().contains(nacionalidadeBusca.toLowerCase())) {
                        autoresParaExibir.add(a);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        if (autoresParaExibir.isEmpty()) {
            System.out.println("Nenhum autor encontrado com o filtro selecionado!");
            return;
        }

        for (int i = 0; i < autoresParaExibir.size(); i++) {
            System.out.println("\n--- Autor " + (i + 1) + " ---");
            System.out.println(autoresParaExibir.get(i).getInfo());
        }

        System.out.println("\nTotal de autores exibidos: " + autoresParaExibir.size());
        System.out.println("Total geral de autores: " + autores.size());
    }
}