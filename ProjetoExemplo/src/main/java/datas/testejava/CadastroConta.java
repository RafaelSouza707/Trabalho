package datas.testejava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroConta extends JFrame {

    private boolean editando = false;
    private Conta conta = new Conta(0, "", "", "", "");

    private JTextField jTextFieldCodigo;
    private JTextField jTextFieldNomeCliente;
    private JTextField jTextFieldCpf;
    private JTextField jTextFieldEmail;
    private JTextField jTextFieldSenha;
    private JButton jButton1;
    private JButton jButton2;
    public int TipoConta;
    public int TipoVolta;

    // Construtor
    public CadastroConta() {
        initComponents();
    }

    public CadastroConta(boolean editando, Conta conta) {
        initComponents();
        this.editando = editando;
        this.conta = conta;
        jTextFieldCodigo.setText(String.valueOf(conta.getIdCliente()));
        if (editando) {
            jTextFieldCodigo.setEditable(false);
        }
        jTextFieldNomeCliente.setText(conta.getNomeCliente());
        jTextFieldCpf.setText(conta.getCpfCliente());
        jTextFieldEmail.setText(conta.getEmail());
        jTextFieldSenha.setText(conta.getSenha());
    }

    public void setTipoConta(int Tipo) {
        this.TipoConta = Tipo;
    }

    // Método para inicializar os componentes da GUI
    private void initComponents() {

        // Criando os componentes
        JLabel jLabel1 = new JLabel("Código");
        JLabel jLabel2 = new JLabel("Nome Cliente");
        JLabel jLabel3 = new JLabel("CPF");
        JLabel jLabel4 = new JLabel("E-Mail");
        JLabel jLabel5 = new JLabel("Senha");

        jTextFieldCodigo = new JTextField();
        jTextFieldNomeCliente = new JTextField();
        jTextFieldCpf = new JTextField();
        jTextFieldEmail = new JTextField();
        jTextFieldSenha = new JPasswordField();

        jButton1 = new JButton("Salvar");
        jButton2 = new JButton("Voltar");

        // Definindo tamanhos preferidos para os campos de texto
        jTextFieldCodigo.setPreferredSize(new Dimension(200, 30));
        jTextFieldNomeCliente.setPreferredSize(new Dimension(200, 30));
        jTextFieldCpf.setPreferredSize(new Dimension(200, 30));
        jTextFieldEmail.setPreferredSize(new Dimension(200, 30));
        jTextFieldSenha.setPreferredSize(new Dimension(200, 30));
        jButton1.setPreferredSize(new Dimension(100, 40));
        jButton2.setPreferredSize(new Dimension(100, 40));

        // Definindo layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Configuração dos componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(jLabel1, gbc);

        gbc.gridx = 1;
        add(jTextFieldCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(jLabel2, gbc);

        gbc.gridx = 1;
        add(jTextFieldNomeCliente, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(jLabel3, gbc);

        gbc.gridx = 1;
        add(jTextFieldCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(jLabel4, gbc);

        gbc.gridx = 1;
        add(jTextFieldEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(jLabel5, gbc);

        gbc.gridx = 1;
        add(jTextFieldSenha, gbc);

        // Posicionando os botões
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;  // Ocupa as duas colunas
        gbc.anchor = GridBagConstraints.CENTER;  // Centraliza horizontalmente
        JPanel panel = new JPanel();  // Painel para centralizar os botões
        panel.add(jButton1);
        panel.add(jButton2);
        add(panel, gbc);

        // Ações dos botões
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        // Configurações da janela
        setTitle("Cadastro de Conta");
        setSize(400, 350); // Ajustado o tamanho para acomodar todos os campos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        this.dispose();
        switch (TipoConta){
            case 0:
                TelaInicial telaInicial = new TelaInicial(); // Instancia a tela inicial
                telaInicial.abrirTela(); // Método para abrir a tela inicial
                break;
            case 1:
                TelaADM adm = new TelaADM();
                adm.abrirTela();
        }
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        if(jTextFieldCodigo.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário inserir o Nome!");
        } else if(jTextFieldCpf.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário inserir o CPF!");
        } else if(jTextFieldEmail.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário inserir o E-mail!");
        } else if(jTextFieldSenha.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Necessário inserir a Senha!");
        }

        ContaDAO clienteAdd = new ContaDAO();
        conta.setNomeCliente(jTextFieldNomeCliente.getText());
        conta.setCpfCliente(jTextFieldCpf.getText());
        conta.setEmail(jTextFieldEmail.getText());
        conta.setSenha(jTextFieldSenha.getText());

        if (!editando) {
            conta.setIdCliente(Integer.parseInt(jTextFieldCodigo.getText()));
            clienteAdd.adicionarConta(conta, TipoConta);

        } else {
            clienteAdd.updateConta(conta);
        }
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
        // Limpa o formulário
        jTextFieldCodigo.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldCpf.setText("");
        jTextFieldEmail.setText("");
        jTextFieldSenha.setText("");

        this.dispose();

        switch (TipoConta){
            case 0:
                TelaInicial telaInicial = new TelaInicial(); // Instancia a tela inicial
                telaInicial.abrirTela(); // Método para abrir a tela inicial
                break;
            case 1:
                TelaADM adm = new TelaADM();
                adm.abrirTela();
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CadastroConta().setVisible(true);
            }
        });
    }
}