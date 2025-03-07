package datas.testejava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class TelaAdministrador extends JFrame {
    private JTable tabelaProdutos;
    private DefaultTableModel modelo;
    private ConnectionPostgreSQL conexao;

    public TelaAdministrador() {
        conexao = new ConnectionPostgreSQL();
        setTitle("Tela Administrador");
        setSize(1250, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Descrição");

        tabelaProdutos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnAlterar = new JButton("Alterar Quantidade");
        JButton btnSair = new JButton("Sair");

        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.SOUTH);

        carregarProdutos();

        btnAdicionar.addActionListener(e -> adicionarProduto());
        btnRemover.addActionListener(e -> removerProduto());
        btnAlterar.addActionListener(e -> alterarQuantidade());
        btnSair.addActionListener(e -> sair());
    }

    private void carregarProdutos() {
        modelo.setRowCount(0);
        String sql = "SELECT * FROM produto";
        try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("qtd"),
                        rs.getString("descricao")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage());
        }
    }

    private void adicionarProduto() {
        String nome = JOptionPane.showInputDialog("Nome do Produto:");
        String precoStr = JOptionPane.showInputDialog("Preço:");
        String quantidadeStr = JOptionPane.showInputDialog("Quantidade:");
        String descricao = JOptionPane.showInputDialog("Descrição:");

        try {
            double preco = Double.parseDouble(precoStr);
            int quantidade = Integer.parseInt(quantidadeStr);

            // Busca a quantidade total de registros na tabela
            String sqlCount = "SELECT COUNT(*) AS total FROM produto";
            int novoId = 1;

            try (Connection conn = conexao.getConection();
                 PreparedStatement stmtCount = conn.prepareStatement(sqlCount);
                 ResultSet rs = stmtCount.executeQuery()) {

                if (rs.next()) {
                    novoId = rs.getInt("total") + 1; // Pega o total e adiciona +1
                }
            }

            // Agora faz a inserção usando o novoId
            String sqlInsert = "INSERT INTO produto (id, nome, preco, qtd, descricao) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = conexao.getConection();
                 PreparedStatement stmt = conn.prepareStatement(sqlInsert)) {

                stmt.setInt(1, novoId);
                stmt.setString(2, nome);
                stmt.setDouble(3, preco);
                stmt.setInt(4, quantidade);
                stmt.setString(5, descricao);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!");
                carregarProdutos();
            }
        } catch (NumberFormatException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar produto: " + e.getMessage());
        }
    }


    private void removerProduto() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            int idProduto = (int) tabelaProdutos.getValueAt(linhaSelecionada, 0);
            String sql = "DELETE FROM produto WHERE id = ?";
            try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idProduto);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!");
                carregarProdutos();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
        }
    }

    private void alterarQuantidade() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            int idProduto = (int) tabelaProdutos.getValueAt(linhaSelecionada, 0);
            String quantidadeStr = JOptionPane.showInputDialog("Nova Quantidade:");

            try {
                int novaQuantidade = Integer.parseInt(quantidadeStr);
                String sql = "UPDATE produto SET qtd = ? WHERE id = ?";
                try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, novaQuantidade);
                    stmt.setInt(2, idProduto);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Quantidade alterada com sucesso!");
                    carregarProdutos();
                }
            } catch (NumberFormatException | SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar quantidade: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para alterar a quantidade.");
        }
    }

    private void sair() {
        dispose();
        TelaADM telaInicial = new TelaADM();
        telaInicial.abrirTela();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaAdministrador().setVisible(true));
    }
}
