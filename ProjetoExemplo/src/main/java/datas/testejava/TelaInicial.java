package datas.testejava;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class TelaInicial {
    private JPanel panel1;
    private JTextField emailField;
    private JPasswordField passwordField1;
    private JButton enviarButton;
    private JButton registrarSeButton;

    private ConnectionPostgreSQL connectionPostgreSQL;

    public TelaInicial() {
        connectionPostgreSQL = new ConnectionPostgreSQL();

        enviarButton.addActionListener(e -> verificarLogin());

        registrarSeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroConta cadastro = new CadastroConta();
                cadastro.setVisible(true);

                // Cria o JFrame da tela inicial e fecha a tela de login
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                if (frame != null) {
                    frame.dispose(); // Fecha a tela atual
                }
            }
        });
    }

    private void verificarLogin() {
        String email = emailField.getText();
        String senha = new String(passwordField1.getPassword());

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Conexão com o banco de dados e verificação
        try {
            conexao = connectionPostgreSQL.getConection();

            // Primeiro verifica se o e-mail está na tabela de administradores
            String sqlAdm = "SELECT * FROM adm WHERE email = ? AND senha = ?";
            stmt = conexao.prepareStatement(sqlAdm);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                // E-mail encontrado na tabela de administradores
                // Obtém a janela da tela de login e fecha
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                if (frame != null) {
                    frame.dispose();  // Fecha a tela de login
                }

                // Abre a tela de administrador
                TelaADM telaADM = new TelaADM();
                telaADM.abrirTela();
            } else {
                // Caso não seja um admin, verifica na tabela cliente
                String sqlCliente = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
                stmt = conexao.prepareStatement(sqlCliente);
                stmt.setString(1, email);
                stmt.setString(2, senha);

                rs = stmt.executeQuery();
                if (rs.next()) {
                    // E-mail encontrado na tabela de clientes
                    // Obtém a janela da tela de login e fecha
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
                    if (frame != null) {
                        frame.dispose();  // Fecha a tela de login
                    }

                    int idCliente = rs.getInt("id");

                    // Abre a tela de produtos
                    TelaProdutos telaProdutos = new TelaProdutos(idCliente);
                    telaProdutos.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail ou senha inválidos!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados!");
        } finally {
            connectionPostgreSQL.close(rs, stmt, conexao);
        }
    }


    // Método para inicializar os componentes e exibir a interface
    public void abrirTela() {
        JFrame frame = new JFrame("Tela Inicial");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centraliza
        frame.setVisible(true);
    }

    // Método principal para inicializar a tela
    public static void main(String[] args) {
        TelaInicial tela = new TelaInicial();
        tela.abrirTela();
    }
}
