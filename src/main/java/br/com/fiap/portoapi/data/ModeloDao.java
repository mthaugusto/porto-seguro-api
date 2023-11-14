package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.Modelo;

public class ModeloDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<Modelo> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_MODELO";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Modelo> modeloList = new ArrayList<>();

                    while (resultSet.next()) {
                        Modelo modelo = new Modelo(
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getString("nm_modelo"),
                                resultSet.getInt("t_marca_t_marca_id")
                        );
                        modeloList.add(modelo);
                    }

                    return modeloList;
                }
            }
        }
    }

    public Modelo findById(String bicicletaNrSerie, String clienteNrCpf) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_MODELO WHERE T_BICICLETA_NR_SERIE = ? AND T_BICICLETA_T_CLIENTE_NR_CPF = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicletaNrSerie);
                instrucao.setString(2, clienteNrCpf);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Modelo modelo = null;

                    if (resultSet.next()) {
                        modelo = new Modelo(
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getString("nm_modelo"),
                                resultSet.getInt("t_marca_t_marca_id")
                        );
                    }

                    return modelo;
                }
            }
        }
    }

    public void delete(String bicicletaNrSerie, String clienteNrCpf) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_MODELO WHERE T_BICICLETA_NR_SERIE = ? AND T_BICICLETA_T_CLIENTE_NR_CPF = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicletaNrSerie);
                instrucao.setString(2, clienteNrCpf);
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Modelo modelo) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_MODELO (t_bicicleta_nr_serie, t_bicicleta_t_cliente_nr_cpf, nm_modelo, t_marca_t_marca_id) VALUES (?, ?, ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, modelo.bicicletaNrSerie());
                instrucao.setString(2, modelo.clienteNrCpf());
                instrucao.setString(3, modelo.nomeModelo());
                instrucao.setInt(4, modelo.marcaId());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(Modelo modelo) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_MODELO SET nm_modelo=?, t_marca_t_marca_id=? WHERE t_bicicleta_nr_serie=? AND t_bicicleta_t_cliente_nr_cpf=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, modelo.nomeModelo());
                instrucao.setInt(2, modelo.marcaId());
                instrucao.setString(3, modelo.bicicletaNrSerie());
                instrucao.setString(4, modelo.clienteNrCpf());
                instrucao.executeUpdate();
            }
        }
    }

    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
}
