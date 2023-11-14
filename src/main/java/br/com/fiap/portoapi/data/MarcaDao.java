package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.Marca;

public class MarcaDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    
    public List<Marca> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_MARCA";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Marca> marcaList = new ArrayList<>();

                    while (resultSet.next()) {
                        Marca marca = new Marca(
                                resultSet.getString("nm_marca"),
                                resultSet.getString("ds_pais_origem"),
                                resultSet.getInt("t_marca_id")
                        );
                        marcaList.add(marca);
                    }

                    return marcaList;
                }
            }
        }
    }

    public Marca findById(int marcaId) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_MARCA WHERE T_MARCA_ID = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, marcaId);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Marca marca = null;

                    if (resultSet.next()) {
                        marca = new Marca(
                                resultSet.getString("nm_marca"),
                                resultSet.getString("ds_pais_origem"),
                                resultSet.getInt("t_marca_id")
                        );
                    }

                    return marca;
                }
            }
        }
    }

    public void delete(Marca marca) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_MARCA WHERE T_MARCA_ID = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, marca.marcaId());
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Marca marca) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_MARCA (nm_marca, ds_pais_origem, t_marca_id) VALUES (?, ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, marca.nomeMarca());
                instrucao.setString(2, marca.paisOrigem());
                instrucao.setInt(3, marca.marcaId());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(Marca marca) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_MARCA SET nm_marca=?, ds_pais_origem=? WHERE t_marca_id=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, marca.nomeMarca());
                instrucao.setString(2, marca.paisOrigem());
                instrucao.setInt(3, marca.marcaId());
                instrucao.executeUpdate();
            }
        }
    }

    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
}
