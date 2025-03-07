package datas.testejava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private ConnectionPostgreSQL postgres;
    private Connection conexao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // Construtor que inicializa a conexão com o banco de dados
    public ProdutoDAO() {
        this.postgres = new ConnectionPostgreSQL();
    }

    // Método para listar todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto"; // Nome da tabela 'produto'

        try {
            // Obtendo conexão
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Iterando pelo ResultSet e criando objetos Produto
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(rs.getInt("id")); // Supondo que a tabela tenha o campo 'id'
                produto.setNomeProduto(rs.getString("nome"));
                produto.setPrecoProduto(rs.getDouble("preco"));
                produto.setQtdEstoqueProduto(rs.getInt("quantidade_estoque"));
                produto.setDescricaoProduto(rs.getString("descricao"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(rs, stmt, conexao); // Fechando os recursos
        }

        return produtos;
    }

    // Método para adicionar um novo produto
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produto (nome, preco, quantidade_estoque, descricao) VALUES (?, ?, ?, ?)";

        try {
            // Obtendo conexão
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setInt(3, produto.getQtdEstoqueProduto());
            stmt.setString(4, produto.getDescricaoProduto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao); // Fechando os recursos
        }
    }

    // Método para atualizar um produto
    public void atualizarProduto(Produto produto) {
        String sql = "UPDATE produto SET nome = ?, preco = ?, quantidade_estoque = ?, descricao = ? WHERE id = ?";

        try {
            // Obtendo conexão
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, produto.getNomeProduto());
            stmt.setDouble(2, produto.getPrecoProduto());
            stmt.setInt(3, produto.getQtdEstoqueProduto());
            stmt.setString(4, produto.getDescricaoProduto());
            stmt.setInt(5, produto.getIdProduto()); // ID do produto
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao); // Fechando os recursos
        }
    }

    // Método para excluir um produto
    public void excluirProduto(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try {
            // Obtendo conexão
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao); // Fechando os recursos
        }
    }
}
