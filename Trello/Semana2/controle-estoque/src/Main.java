public class Main {
    public static void main(String[] args) {
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
        System.out.println("Produtos cadastrados:");
        System.out.println("1. " + nomeProduto1 + " - Preço: R$" + precoProduto1 + " - Quantidade: " + quantidadeProduto1);
        System.out.println("2. " + nomeProduto2 + " - Preço: R$" + precoProduto2 + " - Quantidade: " + quantidadeProduto2);
        System.out.println("3. " + nomeProduto3 + " - Preço: R$" + precoProduto3 + " - Quantidade: " + quantidadeProduto3);
    }
}