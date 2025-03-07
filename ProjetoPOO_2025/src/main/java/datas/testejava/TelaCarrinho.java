package datas.testejava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TelaCarrinho extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private ConnectionPostgreSQL conexao;
    private int idCliente;
    private double valorTotal = 0.0;

    public TelaCarrinho(int idCliente) {
        this.idCliente = idCliente;
        conexao = new ConnectionPostgreSQL();
        setTitle("Carrinho de Compras");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Descrição");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        TableColumn precoColumn = tabela.getColumnModel().getColumn(1);
        precoColumn.setPreferredWidth(80);
        TableColumn quantidadeColumn = tabela.getColumnModel().getColumn(2);
        quantidadeColumn.setPreferredWidth(80);
        TableColumn descricaoColumn = tabela.getColumnModel().getColumn(3);
        descricaoColumn.setPreferredWidth(400);

        carregarDados(idCliente);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(e -> excluirItem());
        painelBotoes.add(btnExcluir);

        JButton btnAlterarQuantidade = new JButton("Alterar Quantidade");
        btnAlterarQuantidade.addActionListener(e -> alterarQuantidade());
        painelBotoes.add(btnAlterarQuantidade);

        JButton btnFinalizarCompra = new JButton("Finalizar Compra");
        btnFinalizarCompra.addActionListener(e -> finalizarCompra());
        painelBotoes.add(btnFinalizarCompra);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltar(idCliente));
        painelBotoes.add(btnVoltar);

        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void carregarDados(int idCliente) {
        String sql = "SELECT p.nome, c.preco, c.qtd, p.descricao FROM carrinho c JOIN produto p ON c.id_produto = p.id WHERE c.id_cliente = ?";

        try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            modelo.setRowCount(0);
            valorTotal = 0.0;  // Resetar o valor total antes de recalcular

            while (rs.next()) {
                double preco = rs.getDouble("preco");
                int quantidade = rs.getInt("qtd");
                double totalItem = preco * quantidade; // Calcular o valor total de cada item
                valorTotal += totalItem; // Somar o valor total de todos os itens

                modelo.addRow(new Object[]{
                        rs.getString("nome"),
                        preco,
                        quantidade,
                        rs.getString("descricao")
                });
            }

            // Adicionar a linha de total
            modelo.addRow(new Object[]{"", "", "Total", valorTotal});

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + e.getMessage());
        }
    }

    private void excluirItem() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            String nomeProduto = (String) tabela.getValueAt(linhaSelecionada, 0);
            String sql = "DELETE FROM carrinho WHERE id_cliente = ? AND id_produto = (SELECT id FROM produto WHERE nome = ?)";
            try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                stmt.setString(2, nomeProduto);
                stmt.executeUpdate();
                modelo.removeRow(linhaSelecionada);
                JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");

                // Recalcular o total após exclusão
                carregarDados(idCliente);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir item: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para excluir.");
        }
    }

    private void alterarQuantidade() {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada != -1) {
            String nomeProduto = (String) tabela.getValueAt(linhaSelecionada, 0);
            String quantidadeStr = JOptionPane.showInputDialog(this, "Informe a nova quantidade:");
            try {
                int novaQuantidade = Integer.parseInt(quantidadeStr);
                if (novaQuantidade <= 0) throw new NumberFormatException();
                String sql = "UPDATE carrinho SET qtd = ? WHERE id_cliente = ? AND id_produto = (SELECT id FROM produto WHERE nome = ?)";
                try (Connection conn = conexao.getConection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, novaQuantidade);
                    stmt.setInt(2, idCliente);
                    stmt.setString(3, nomeProduto);
                    stmt.executeUpdate();

                    modelo.setValueAt(novaQuantidade, linhaSelecionada, 2);
                    JOptionPane.showMessageDialog(this, "Quantidade alterada com sucesso!");

                    // Recalcular o total após alteração de quantidade
                    carregarDados(idCliente);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Quantidade inválida.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar quantidade: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para alterar a quantidade.");
        }
    }

    private void finalizarCompra() {
        String sqlSelect = "SELECT c.id_produto, c.qtd FROM carrinho c WHERE c.id_cliente = ?";
        String sqlUpdate = "UPDATE produto SET qtd = qtd - ? WHERE id = ?";
        String sqlDelete = "DELETE FROM carrinho WHERE id_cliente = ?";

        try (Connection conn = conexao.getConection();
             PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
             PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
             PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete)) {

            // 1. Buscar todos os produtos no carrinho
            stmtSelect.setInt(1, idCliente);
            ResultSet rs = stmtSelect.executeQuery();

            while (rs.next()) {
                int idProduto = rs.getInt("id_produto");
                int quantidade = rs.getInt("qtd");

                // 2. Atualizar a quantidade do produto
                stmtUpdate.setInt(1, quantidade);
                stmtUpdate.setInt(2, idProduto);
                stmtUpdate.executeUpdate();
            }

            // 3. Apagar todos os produtos do carrinho
            stmtDelete.setInt(1, idCliente);
            stmtDelete.executeUpdate();

            JOptionPane.showMessageDialog(this, "Compra finalizada com sucesso!");

            // Recarregar a tabela para limpar os itens
            modelo.setRowCount(0);
            valorTotal = 0.0;
            carregarDados(idCliente);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao finalizar a compra: " + e.getMessage());
        }
    }


    private void voltar(int getIdCliente) {
        dispose();
        TelaProdutos telaProdutos = new TelaProdutos(getIdCliente);
        telaProdutos.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCarrinho(1));
    }
}
