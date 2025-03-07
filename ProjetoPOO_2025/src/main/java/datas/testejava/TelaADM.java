package datas.testejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaADM {
    private JPanel panel1;
    private JButton controleDeCadastrosButton;
    private JButton controleDeProdutosButton;
    private JButton adicionarADMButton;
    private JButton sairButton;

    public TelaADM() {
        // Inicializando o painel principal
        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 2, 20, 20)); // 2 linhas, 2 colunas, espaçamento de 20px
        panel1.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Margens de 50px nas bordas

        controleDeCadastrosButton = new JButton("Controle de Cadastros");
        controleDeProdutosButton = new JButton("Controle de Produtos");
        adicionarADMButton = new JButton("Adicionar ADM");
        sairButton = new JButton("Sair");

        // Adicionando botões ao painel
        panel1.add(controleDeCadastrosButton);
        panel1.add(controleDeProdutosButton);
        panel1.add(adicionarADMButton);
        panel1.add(sairButton);

        // Ações dos botões
        controleDeProdutosButton.addActionListener(e -> {
            TelaAdministrador telaAdministrador = new TelaAdministrador();
            telaAdministrador.setVisible(true);
            fecharTela();
        });

        controleDeCadastrosButton.addActionListener(e -> {
            TelaControleCliente telaControleCliente = new TelaControleCliente();
            telaControleCliente.setVisible(true);
            fecharTela();
        });

        adicionarADMButton.addActionListener(e -> {
            CadastroConta cadastroADM = new CadastroConta();
            cadastroADM.setTipoConta(1);
            cadastroADM.setVisible(true);
            fecharTela();
        });

        sairButton.addActionListener(e -> {
            int resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (resposta == JOptionPane.YES_OPTION) {
                TelaInicial tela= new TelaInicial();
                tela.abrirTela();
                fecharTela();
            }
        });
    }

    public void abrirTela() {
        JFrame frame = new JFrame("Tela ADM");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null); // Centraliza
        frame.setVisible(true);
    }

    private void fecharTela() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(panel1);
        if (frame != null) {
            frame.dispose();
        }
    }

    public static void main(String[] args) {
        TelaADM tela = new TelaADM();
        tela.abrirTela();
    }
}