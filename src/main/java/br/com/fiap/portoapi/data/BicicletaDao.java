package br.com.fiap.portoapi.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fiap.portoapi.model.Bicicleta;

public class BicicletaDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<Bicicleta> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_BICICLETA";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Bicicleta> bicicletaList = new ArrayList<>();

                    while (resultSet.next()) {
                        Bicicleta bicicleta = new Bicicleta(
                                resultSet.getString("t_cliente_nr_cpf"),
                                resultSet.getString("nr_serie"),
                                resultSet.getInt("nr_ano_fabricacao"),
                                formatarData(resultSet.getDate("dt_compra")),
                                resultSet.getDouble("vl_avaliado"),
                                resultSet.getDouble("vl_fabrica"),
                                resultSet.getString("st_situacao"),
                                resultSet.getString("st_tipo")
                        );
                        bicicletaList.add(bicicleta);
                    }

                    return bicicletaList;
                }
            }
        }
    }

    public Bicicleta findByNrSerie(String nrSerie) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_BICICLETA WHERE NR_SERIE = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, nrSerie);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Bicicleta bicicleta = null;

                    if (resultSet.next()) {
                        bicicleta = new Bicicleta(
                                resultSet.getString("t_cliente_nr_cpf"),
                                resultSet.getString("nr_serie"),
                                resultSet.getInt("nr_ano_fabricacao"),
                                formatarData(resultSet.getDate("dt_compra")),
                                resultSet.getDouble("vl_avaliado"),
                                resultSet.getDouble("vl_fabrica"),
                                resultSet.getString("st_situacao"),
                                resultSet.getString("st_tipo")
                        );
                    }

                    return bicicleta;
                }
            }
        }
    }

    public void delete(Bicicleta bicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_BICICLETA WHERE NR_SERIE = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicleta.nrSerie());
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Bicicleta bicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_BICICLETA (t_cliente_nr_cpf, nr_serie, nr_ano_fabricacao, dt_compra, vl_avaliado, vl_fabrica, st_situacao, st_tipo) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?, ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicleta.clienteNrCpf());
                instrucao.setString(2, bicicleta.nrSerie());
                instrucao.setInt(3, bicicleta.anoFabricacao());
                instrucao.setString(4, bicicleta.dtCompra());
                instrucao.setDouble(5, bicicleta.vlAvaliado());
                instrucao.setDouble(6, bicicleta.vlFabrica());
                instrucao.setString(7, bicicleta.situacao());
                instrucao.setString(8, bicicleta.tipo());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(Bicicleta bicicleta) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_BICICLETA SET nr_ano_fabricacao=?, dt_compra=TO_DATE(?, 'YYYY-MM-DD'), vl_avaliado=?, vl_fabrica=?, st_situacao=?, st_tipo=? WHERE nr_serie=? AND t_cliente_nr_cpf=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setInt(1, bicicleta.anoFabricacao());
                instrucao.setString(2, bicicleta.dtCompra());
                instrucao.setDouble(3, bicicleta.vlAvaliado());
                instrucao.setDouble(4, bicicleta.vlFabrica());
                instrucao.setString(5, bicicleta.situacao());
                instrucao.setString(6, bicicleta.tipo());
                instrucao.setString(7, bicicleta.nrSerie());
                instrucao.setString(8, bicicleta.clienteNrCpf());
                instrucao.executeUpdate();
            }
        }
    }
    
   
    private void classForName() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }
    
    private String formatarData(Date data) {
        if (data == null) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(data);
    }
}
