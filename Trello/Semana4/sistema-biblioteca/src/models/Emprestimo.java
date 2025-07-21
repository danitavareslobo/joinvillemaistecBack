package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Livro livro;
    private String nomeUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean devolvido;
    private static int totalEmprestimos = 0;

    public Emprestimo(Livro livro, String nomeUsuario) {
        this.livro = livro;
        this.nomeUsuario = nomeUsuario;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(14);
        this.devolvido = false;
        totalEmprestimos++;
        livro.setDisponivel(false);
    }

    public Livro getLivro() {
        return livro;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void realizarDevolucao() {
        this.devolvido = true;
        this.livro.setDisponivel(true);
    }

    public boolean isAtrasado() {
        return !devolvido && LocalDate.now().isAfter(dataDevolucao);
    }

    public long diasAtraso() {
        if (!isAtrasado()) {
            return 0;
        }
        return LocalDate.now().toEpochDay() - dataDevolucao.toEpochDay();
    }

    public static int getTotalEmprestimos() {
        return totalEmprestimos;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String statusEmprestimo = devolvido ? "Devolvido" : (isAtrasado() ? "ATRASADO" : "Em andamento");

        StringBuilder info = new StringBuilder();
        info.append(String.format("Usuário: %s | Livro: %s", nomeUsuario, livro.getTitulo()));
        info.append(String.format("\nEmpréstimo: %s | Devolução: %s | Status: %s",
                dataEmprestimo.format(formatter),
                dataDevolucao.format(formatter),
                statusEmprestimo));

        if (isAtrasado()) {
            info.append(String.format(" (%d dias de atraso)", diasAtraso()));
        }

        return info.toString();
    }
}