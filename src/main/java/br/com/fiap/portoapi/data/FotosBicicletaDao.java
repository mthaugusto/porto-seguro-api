package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.portoapi.model.FotosBicicleta;

public class FotosBicicletaDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<FotosBicicleta> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_FOTOS_BICICLETA";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<FotosBicicleta> fotosBicicletaList = new ArrayList<>();

                    while (resultSet.next()) {
                        FotosBicicleta fotosBicicleta = new FotosBicicleta(
                                resultSet.getInt("id_foto"),
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getBytes("foto")
                        );
                        fotosBicicletaList.add(fotosBicicleta);
                    }

                    return fotosBicicletaList;
                }
            }
        }
    }

    public FotosBicicleta findById(int idFoto) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_FOTOS_BICICLETA WHERE ID_FOTO = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, idFoto);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    FotosBicicleta fotosBicicleta = null;

                    if (resultSet.next()) {
                        fotosBicicleta = new FotosBicicleta(
                                resultSet.getInt("id_foto"),
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getBytes("foto")
                        );
                    }

                    return fotosBicicleta;
                }
            }
        }
    }

    public void delete(FotosBicicleta fotosBicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_FOTOS_BICICLETA WHERE ID_FOTO = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, fotosBicicleta.idFoto());
                instrucao.executeUpdate();
            }
        }
    }

    public void create(FotosBicicleta fotosBicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_FOTOS_BICICLETA (id_foto, t_bicicleta_nr_serie, t_bicicleta_t_cliente_nr_cpf, foto) VALUES (?, ?, ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, fotosBicicleta.idFoto());
                instrucao.setString(2, fotosBicicleta.bicicletaNrSerie());
                instrucao.setString(3, fotosBicicleta.clienteNrCpf());
                instrucao.setBytes(4, fotosBicicleta.foto());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(FotosBicicleta fotosBicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_FOTOS_BICICLETA SET t_bicicleta_nr_serie=?, t_bicicleta_t_cliente_nr_cpf=?, foto=? WHERE id_foto=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, fotosBicicleta.bicicletaNrSerie());
                instrucao.setString(2, fotosBicicleta.clienteNrCpf());
                instrucao.setBytes(3, fotosBicicleta.foto());
                instrucao.setInt(4, fotosBicicleta.idFoto());
                instrucao.executeUpdate();
            }
        }
    }

    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
}
