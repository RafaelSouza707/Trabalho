package datas.testejava;


/**
 *
 * @author Jose Barros
 */
public class Conta {

    private int idCliente;
    private String nomeCliente, cpfCliente, email, senha;

    public Conta(int idCliente, String nomeCliente,String cpfCliente, String email, String senha) {
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.email = email;
        this.senha = senha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Conta{" + "idCliente=" + idCliente + ", nomeCliente=" + nomeCliente + "cpfCliente" + cpfCliente + "email" + email +'}';
    }
}
