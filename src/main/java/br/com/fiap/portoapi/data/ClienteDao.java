package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.Cliente;

public class ClienteDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<Cliente> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_CLIENTE";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Cliente> clienteList = new ArrayList<>();

                    while (resultSet.next()) {
                        Cliente cliente = new Cliente(
                                resultSet.getString("nr_cpf"),
                                resultSet.getString("nm_cliente"),
                                resultSet.getString("ml_cliente"),
                                resultSet.getString("nr_telefone"),
                                resultSet.getString("ad_cliente"),
                                resultSet.getString("gn_cliente").charAt(0),
                                resultSet.getString("pf_cliente"),
                                resultSet.getDouble("vl_renda"),
                                resultSet.getString("st_estado_civil"),
                                formatarData(resultSet.getDate("dt_nascimento")),
                                formatarData(resultSet.getDate("dt_cadastro"))
                        );
                        clienteList.add(cliente);
                    }

                    return clienteList;
                }
            }
        }
    }

    public Cliente findByCpf(String cpf) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_CLIENTE WHERE NR_CPF = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, cpf);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Cliente cliente = null;

                    if (resultSet.next()) {
                        cliente = new Cliente(
                                resultSet.getString("nr_cpf"),
                                resultSet.getString("nm_cliente"),
                                resultSet.getString("ml_cliente"),
                                resultSet.getString("nr_telefone"),
                                resultSet.getString("ad_cliente"),
                                resultSet.getString("gn_cliente").charAt(0),
                                resultSet.getString("pf_cliente"),
                                resultSet.getDouble("vl_renda"),
                                resultSet.getString("st_estado_civil"),
                                formatarData(resultSet.getDate("dt_nascimento")),
                                formatarData(resultSet.getDate("dt_cadastro"))
                        );
                    }

                    return cliente;
                }
            }
        }
    }

    public void delete(Cliente cliente) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_CLIENTE WHERE NR_CPF = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, cliente.nrCpf());
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Cliente cliente) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_CLIENTE (nr_cpf, nm_cliente, ml_cliente, nr_telefone, ad_cliente, gn_cliente, pf_cliente, vl_renda, st_estado_civil, dt_nascimento, dt_cadastro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'))";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, cliente.nrCpf());
                instrucao.setString(2, cliente.nomeCliente());
                instrucao.setString(3, cliente.emailCliente());
                instrucao.setString(4, cliente.nrTelefone());
                instrucao.setString(5, cliente.enderecoCliente());
                instrucao.setString(6, String.valueOf(cliente.generoCliente()));
                instrucao.setString(7, cliente.tipoCliente());
                instrucao.setDouble(8, cliente.renda());
                instrucao.setString(9, cliente.estadoCivil());
                instrucao.setString(10, cliente.dtNascimento());
                instrucao.setString(11, cliente.dtCadastro());


                instrucao.executeUpdate();
            }
        }
    }

    public void update(Cliente cliente) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_CLIENTE SET nm_cliente=?, ml_cliente=?, nr_telefone=?, ad_cliente=?, gn_cliente=?, pf_cliente=?, vl_renda=?, st_estado_civil=?, dt_nascimento=TO_DATE(?, 'YYYY/MM/DD'), dt_cadastro=TO_DATE(?, 'YYYY/MM/DD') WHERE nr_cpf=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, cliente.nomeCliente());
                instrucao.setString(2, cliente.emailCliente());
                instrucao.setString(3, cliente.nrTelefone());
                instrucao.setString(4, cliente.enderecoCliente());
                instrucao.setString(5, String.valueOf(cliente.generoCliente()));
                instrucao.setString(6, cliente.tipoCliente());
                instrucao.setDouble(7, cliente.renda());
                instrucao.setString(8, cliente.estadoCivil());
                instrucao.setString(9, cliente.dtNascimento());
                instrucao.setString(10, cliente.dtCadastro());
                instrucao.setString(11, cliente.nrCpf());
                instrucao.executeUpdate();
            }
        }
    }

    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    
    // Função para retornar apenas a data - que estava sendo retornada com um 00:00:00 do horário
    private String formatarData(Date data) {
        if (data == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(data);
    }
}
