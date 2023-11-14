package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.Acessorios;

public class AcessoriosDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<Acessorios> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_ACESSORIOS";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Acessorios> acessoriosList = new ArrayList<>();

                    while (resultSet.next()) {
                        Acessorios acessorios = new Acessorios(
                                resultSet.getString("st_velocimetro"),
                                resultSet.getString("st_gps"),
                                resultSet.getString("st_ciclocomputador"),
                                resultSet.getInt("t_acessorios_id"),
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf")
                        );
                        acessoriosList.add(acessorios);
                    }

                    return acessoriosList;
                }
            }
        }
    }

    public Acessorios findById(int id) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_ACESSORIOS WHERE T_ACESSORIOS_ID = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, id);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Acessorios acessorios = null;

                    if (resultSet.next()) {
                        acessorios = new Acessorios(
                                resultSet.getString("st_velocimetro"),
                                resultSet.getString("st_gps"),
                                resultSet.getString("st_ciclocomputador"),
                                resultSet.getInt("t_acessorios_id"),
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf")
                        );
                    }

                    return acessorios;
                }
            }
        }
    }

    public void delete(Acessorios acessorios) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_ACESSORIOS WHERE T_ACESSORIOS_ID = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, acessorios.acessoriosId());
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Acessorios acessorios) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_ACESSORIOS (st_velocimetro, st_gps, st_ciclocomputador, t_acessorios_id, t_bicicleta_nr_serie, t_bicicleta_t_cliente_nr_cpf) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, acessorios.velocimetro());
                instrucao.setString(2, acessorios.gps());
                instrucao.setString(3, acessorios.ciclocomputador());
                instrucao.setInt(4, acessorios.acessoriosId());
                instrucao.setString(5, acessorios.bicicletaNrSerie());
                instrucao.setString(6, acessorios.clienteNrCpf());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(Acessorios acessorios) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_ACESSORIOS SET st_velocimetro=?, st_gps=?, st_ciclocomputador=? WHERE t_acessorios_id=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, acessorios.velocimetro());
                instrucao.setString(2, acessorios.gps());
                instrucao.setString(3, acessorios.ciclocomputador());
                instrucao.setInt(4, acessorios.acessoriosId());
                instrucao.executeUpdate();
            }
        }
    }

    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
}
