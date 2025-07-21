package models;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> acervo;
    private List<Emprestimo> emprestimos;
    private static int totalBibliotecas = 0;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.acervo = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        totalBibliotecas++;
    }

    public void cadastrarLivro(Livro livro) {
        if (buscarLivroPorIsbn(livro.getIsbn()) != null) {
            System.out.println("Erro: Já existe um livro com este ISBN!");
            return;
        }
        acervo.add(livro);
        System.out.println("Livro cadastrado com sucesso!");
    }

    public boolean editarLivro(String isbn, String novoTitulo, String novoAutor, int novoAno) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro == null) {
            return false;
        }

        livro.setTitulo(novoTitulo);
        livro.setAutor(novoAutor);
        livro.setAnoPublicacao(novoAno);
        return true;
    }

    public boolean removerLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro == null) {
            return false;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Erro: Não é possível remover um livro emprestado!");
            return false;
        }

        acervo.remove(livro);
        return true;
    }

    public void listarTodosLivros() {
        if (acervo.isEmpty()) {
            System.out.println("Nenhum livro cadastrado no acervo!");
            return;
        }

        System.out.println("\n=== ACERVO COMPLETO ===");
        for (Livro livro : acervo) {
            System.out.println(livro.toString());
        }
        System.out.println("Total de livros: " + acervo.size());
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : acervo) {
            if (livro.getIsbn().equalsIgnoreCase(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                livrosEncontrados.add(livro);
            }
        }
        return livrosEncontrados;
    }

    public boolean registrarEmprestimo(String isbn, String nomeUsuario) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro == null) {
            System.out.println("Erro: Livro não encontrado!");
            return false;
        }

        if (!livro.isDisponivel()) {
            System.out.println("Erro: Livro não está disponível para empréstimo!");
            return false;
        }

        Emprestimo novoEmprestimo = new Emprestimo(livro, nomeUsuario);
        emprestimos.add(novoEmprestimo);
        System.out.println("Empréstimo registrado com sucesso!");
        return true;
    }

    public boolean registrarDevolucao(String isbn) {
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivro().getIsbn().equalsIgnoreCase(isbn) && !emprestimo.isDevolvido()) {
                emprestimo.realizarDevolucao();
                System.out.println("Devolução registrada com sucesso!");
                return true;
            }
        }
        System.out.println("Erro: Empréstimo não encontrado ou já devolvido!");
        return false;
    }

    public void listarLivrosEmprestados() {
        List<Emprestimo> emprestimosAtivos = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido()) {
                emprestimosAtivos.add(emprestimo);
            }
        }

        if (emprestimosAtivos.isEmpty()) {
            System.out.println("Nenhum livro emprestado no momento!");
            return;
        }

        System.out.println("\n=== LIVROS EMPRESTADOS ===");
        for (Emprestimo emprestimo : emprestimosAtivos) {
            System.out.println(emprestimo.toString());
            System.out.println("---");
        }
    }

    public void listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.isDisponivel()) {
                disponiveis.add(livro);
            }
        }

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum livro disponível no momento!");
            return;
        }

        System.out.println("\n=== LIVROS DISPONÍVEIS ===");
        for (Livro livro : disponiveis) {
            System.out.println(livro.toString());
        }
        System.out.println("Total disponível: " + disponiveis.size());
    }

    public void exibirEstatisticas() {
        int livrosEmprestados = 0;
        int emprestimosAtrasados = 0;

        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido()) {
                livrosEmprestados++;
                if (emprestimo.isAtrasado()) {
                    emprestimosAtrasados++;
                }
            }
        }

        System.out.println("\n=== ESTATÍSTICAS DE EMPRÉSTIMOS ===");
        System.out.println("Total de empréstimos realizados: " + emprestimos.size());
        System.out.println("Livros atualmente emprestados: " + livrosEmprestados);
        System.out.println("Empréstimos em atraso: " + emprestimosAtrasados);
        System.out.println("Livros disponíveis: " + (acervo.size() - livrosEmprestados));
    }

    public String getNome() {
        return nome;
    }

    public int getTotalLivrosAcervo() {
        return acervo.size();
    }

    public static int getTotalBibliotecas() {
        return totalBibliotecas;
    }
}