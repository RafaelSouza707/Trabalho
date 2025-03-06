package datas.testejava;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaControleCliente extends javax.swing.JFrame {

    public TelaControleCliente() {
        initComponents();
        pesquisarClientes();
    }

    private void initComponents() {

        jButton1 = new JButton("Cadastrar");
        jButton2 = new JButton("Editar");
        jButton3 = new JButton("Excluir");
        jButton4 = new JButton("Sair");
        jButton5 = new JButton("Pesquisar");
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID", "Cliente", "CPF", "E-mail"}
        ) {
            Class[] types = {Integer.class, String.class, String.class, String.class};
            boolean[] canEdit = {false, false, false, false};

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);

        jScrollPane1.setViewportView(jTable1);

        jButton1.addActionListener(evt -> {
            CadastroConta cadastro = new CadastroConta();
            cadastro.setTipoConta(1);
            cadastro.setVisible(true);
            dispose();
        });

        jButton2.addActionListener(evt -> editarCliente());

        jButton3.addActionListener(evt -> excluirCliente());

        jButton4.addActionListener(evt -> {
            TelaADM adm = new TelaADM();
            adm.abrirTela();
            dispose(); // Fecha a tela atual
        });

        jButton5.addActionListener(evt -> pesquisarClientes());

        // Layout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(jButton5)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton5)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3)
                                        .addComponent(jButton4))
                                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void pesquisarClientes() {
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        modelo.setNumRows(0);

        ContaDAO banco = new ContaDAO();
        for (Conta c : banco.listarContas()) {
            modelo.addRow(new Object[]{
                    c.getIdCliente(),
                    c.getNomeCliente(),
                    c.getCpfCliente(),
                    c.getEmail()
            });
        }
    }

    private void editarCliente() {
        int linha = jTable1.getSelectedRow();
        if (linha >= 0) {
            int id = (int) jTable1.getValueAt(linha, 0);
            String nome = (String) jTable1.getValueAt(linha, 1);
            String cpf = (String) jTable1.getValueAt(linha, 2);
            String email = (String) jTable1.getValueAt(linha, 3);

            Conta conta = new Conta(id, nome, cpf, email, ""); // Senha não é exibida
            CadastroConta cadastro = new CadastroConta(true, conta);
            cadastro.setVisible(true);

            pesquisarClientes(); // Atualiza após edição
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para editar.");
        }
    }

    private void excluirCliente() {
        int linha = jTable1.getSelectedRow();
        if (linha >= 0) {
            int id = (int) jTable1.getValueAt(linha, 0);
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o cliente?", "Excluir", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ContaDAO banco = new ContaDAO();
                banco.deletarConta(id);
                pesquisarClientes();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente para excluir.");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception ex) {
            System.err.println("Falha ao carregar o tema.");
        }

        java.awt.EventQueue.invokeLater(() -> new TelaControleCliente().setVisible(true));
    }

    private JButton jButton1, jButton2, jButton3, jButton4, jButton5;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
}
