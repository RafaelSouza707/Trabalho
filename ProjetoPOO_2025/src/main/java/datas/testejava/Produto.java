package datas.testejava;

public class Produto {
    private int id; // Suponha que o produto tenha um ID único
    private String nome;
    private double preco;
    private int qtdEstoque;
    private String descricao;

    // Getters e Setters
    public int getIdProduto() {
        return id;
    }
    public void setIdProduto(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nome;
    }
    public void setNomeProduto(String nome) {
        this.nome = nome;
    }

    public double getPrecoProduto() {
        return preco;
    }
    public void setPrecoProduto(double preco) {
        this.preco = preco;
    }

    public int getQtdEstoqueProduto() {
        return qtdEstoque;
    }
    public void setQtdEstoqueProduto(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public String getDescricaoProduto() {
        return descricao;
    }
    public void setDescricaoProduto(String descricao) {
        this.descricao = descricao;
    }
}
