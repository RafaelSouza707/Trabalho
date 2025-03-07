package datas.testejava;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class TelaProdutos extends JFrame {
    private JTable tabelaProdutos;
    private JButton btnAtualizar, btnAddCarrinho, btnCarrinho;
    private DefaultTableModel modelo;
    private ArrayList<String> carrinho;
    private int idCliente;

    public TelaProdutos(int idCliente) {
        this.idCliente = idCliente;
        setTitle("Lista de Produtos");
        setSize(1250, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        carrinho = new ArrayList<>();

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Descrição");

        tabelaProdutos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        // Chama o método que define a largura das colunas
        definirLarguraColunas();

        // Definir cabeçalho em negrito
        JTableHeader header = tabelaProdutos.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));  // Cabeçalho em negrito

        JPanel painelBotoes = new JPanel();
        btnAtualizar = new JButton("Atualizar");
        btnAddCarrinho = new JButton("Adicionar ao Carrinho");
        btnCarrinho = new JButton("Carrinho");
        JButton btnSair = new JButton("Sair");


        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnAddCarrinho);
        painelBotoes.add(btnCarrinho);
        painelBotoes.add(btnSair);
        add(painelBotoes, BorderLayout.SOUTH);


        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProdutos();
            }
        });

        btnAddCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoCarrinho();
            }
        });

        btnCarrinho.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (idCliente != -1) {
                    TelaCarrinho carrinhoDeCompras = new TelaCarrinho(idCliente);
                    carrinhoDeCompras.setVisible(true);
                    dispose();

                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
                if (opcao == JOptionPane.YES_OPTION) {
                    dispose(); // Fecha a tela atual
                    TelaInicial telaInicial = new TelaInicial();
                    telaInicial.abrirTela();
                }
            }
        });


        atualizarProdutos();
    }


    private void atualizarProdutos() {
        modelo.setRowCount(0);
        ConnectionPostgreSQL conexao = new ConnectionPostgreSQL();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexao.getConection();
            String sql = "SELECT id, nome, preco, qtd, descricao FROM produto ORDER BY id ASC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

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
            JOptionPane.showMessageDialog(null, "Erro ao buscar produtos: " + e.getMessage());
        } finally {
            conexao.close(rs, stmt, conn);
        }
    }

    private void adicionarAoCarrinho() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            int idProduto = (int) tabelaProdutos.getValueAt(linhaSelecionada, 0);
            double precoProduto = (double) tabelaProdutos.getValueAt(linhaSelecionada, 2);
            int qtdProduto = 1; // Sempre começa com 1 unidade

            ConnectionPostgreSQL conexao = new ConnectionPostgreSQL();
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            try {
                conn = conexao.getConection();

                // 1. Verifica se o produto já existe no carrinho
                String verificaSql = "SELECT qtd FROM carrinho WHERE id_cliente = ? AND id_produto = ?";
                stmt = conn.prepareStatement(verificaSql);
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idProduto);
                rs = stmt.executeQuery();

                if (rs.next()) { // Produto já existe
                    int quantidadeAtual = rs.getInt("qtd");
                    int novaQuantidade = quantidadeAtual + 1;

                    // 2. Atualiza a quantidade
                    String updateSql = "UPDATE carrinho SET qtd = ? WHERE id_cliente = ? AND id_produto = ?";
                    stmt = conn.prepareStatement(updateSql);
                    stmt.setInt(1, novaQuantidade);
                    stmt.setInt(2, idCliente);
                    stmt.setInt(3, idProduto);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Quantidade atualizada no carrinho!");

                } else {
                    // 3. Se não existir, insere o produto normalmente
                    String insertSql = "INSERT INTO carrinho (id_cliente, id_produto, preco, qtd) VALUES (?, ?, ?, ?)";
                    stmt = conn.prepareStatement(insertSql);
                    stmt.setInt(1, idCliente);
                    stmt.setInt(2, idProduto);
                    stmt.setDouble(3, precoProduto);
                    stmt.setInt(4, qtdProduto);
                    stmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho!");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar ao carrinho: " + e.getMessage());
            } finally {
                conexao.close(rs, stmt, conn);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um produto para adicionar ao carrinho!");
        }
    }


    private void definirLarguraColunas() {
        tabelaProdutos.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabelaProdutos.getColumnModel().getColumn(2).setPreferredWidth(80);
        tabelaProdutos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tabelaProdutos.getColumnModel().getColumn(3).setPreferredWidth(80);
        tabelaProdutos.getColumnModel().getColumn(4).setPreferredWidth(300);
    }

    public int getIdCliente(){
        return idCliente;
    }

    public static void main(String[] args) {
        int idCliente = 1; // Simulando que o cliente logado tem ID 1
        SwingUtilities.invokeLater(() -> {
            TelaProdutos tela = new TelaProdutos(idCliente); // Passa o idCliente direto aqui
            tela.setVisible(true);
        });
    }

}
