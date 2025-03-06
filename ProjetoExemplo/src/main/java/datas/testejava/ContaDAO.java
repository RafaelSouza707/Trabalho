package datas.testejava;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContaDAO {

    public void adicionarConta(Conta conta, int tipoConta) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        switch (tipoConta) {
            case 0:
                try {
                    conexao = postgres.getConection();
                    stmt = conexao.prepareStatement("INSERT INTO cliente(id, nome, cpf, email, senha) VALUES(?,?,?,?,?)");
                    stmt.setInt(1, conta.getIdCliente());
                    stmt.setString(2, conta.getNomeCliente());
                    stmt.setString(3, conta.getCpfCliente());
                    stmt.setString(4, conta.getEmail());
                    stmt.setString(5, conta.getSenha());

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    postgres.close(stmt, conexao);
                }
                break;
            case 1:
                try {
                    conexao = postgres.getConection();
                    stmt = conexao.prepareStatement("INSERT INTO adm(id, nome, cpf, email, senha) VALUES(?,?,?,?,?)");
                    stmt.setInt(1, conta.getIdCliente());
                    stmt.setString(2, conta.getNomeCliente());
                    stmt.setString(3, conta.getCpfCliente());
                    stmt.setString(4, conta.getEmail());
                    stmt.setString(5, conta.getSenha());

                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    postgres.close(stmt, conexao);
                }
                break;
        }
    }

    public List<Conta> listarContas() {
        List<Conta> listaRetorno = new LinkedList<>();
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();

        try (Connection conexao = postgres.getConection();
             PreparedStatement stmt = conexao.prepareStatement("SELECT id, nome, cpf, email FROM cliente ORDER BY id");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Conta conta = new Conta(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        "");
                listaRetorno.add(conta);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaRetorno;
    }

    public void updateConta(Conta conta) {
        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("UPDATE cliente SET nome=?, cpf=?, email=?, senha=?, WHERE id=?");
            stmt.setString(1, conta.getNomeCliente());
            stmt.setString(2, conta.getCpfCliente());
            stmt.setString(3, conta.getEmail());
            stmt.setString(4, conta.getSenha());
            stmt.setInt(5, conta.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(stmt, conexao);
        }
    }
    public void deletarConta(int codigo) {

        ConnectionPostgreSQL postgres = new ConnectionPostgreSQL();
        PreparedStatement stmt = null;
        Connection conexao = null;
        try {
            conexao = postgres.getConection();
            stmt = conexao.prepareStatement("DELETE FROM cliente WHERE id=?");
            stmt.setInt(1, codigo);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            postgres.close(null, stmt, conexao);
        }
    }
}
