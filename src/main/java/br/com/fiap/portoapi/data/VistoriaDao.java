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

import br.com.fiap.portoapi.model.Vistoria;

public class VistoriaDao {

    private final String USER = "rm99697";
    private final String PASS = "111294";
    private final String URL = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";

    public List<Vistoria> findAll() throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_VISTORIA";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    List<Vistoria> vistoriaList = new ArrayList<>();

                    while (resultSet.next()) {
                        Vistoria vistoria = new Vistoria(
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getInt("id_vistoria"),
                                formatarData(resultSet.getDate("dt_vistoria")),
                                resultSet.getString("st_resultado"),
                                resultSet.getString("st_estado_conservacao")
                        );
                        vistoriaList.add(vistoria);
                    }

                    return vistoriaList;
                }
            }
        }
    }
    public Vistoria findBySerieNr(String bicicletaNrSerie) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM T_VISTORIA WHERE T_BICICLETA_NR_SERIE = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicletaNrSerie);
                try (ResultSet resultSet = instrucao.executeQuery()) {
                    Vistoria vistoria = null;

                    if (resultSet.next()) {
                        vistoria = new Vistoria(
                                resultSet.getString("t_bicicleta_nr_serie"),
                                resultSet.getString("t_bicicleta_t_cliente_nr_cpf"),
                                resultSet.getInt("id_vistoria"),
                                formatarData(resultSet.getDate("dt_vistoria")),
                                resultSet.getString("st_resultado"),
                                resultSet.getString("st_estado_conservacao")
                        );
                    }

                    return vistoria;
                }
            }
        }
    }

    public void delete(String bicicletaNrSerie) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM T_VISTORIA WHERE T_BICICLETA_NR_SERIE = ?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, bicicletaNrSerie);
                instrucao.executeUpdate();
            }
        }
    }

    public void create(Vistoria vistoria) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO T_VISTORIA (T_BICICLETA_NR_SERIE, T_BICICLETA_T_CLIENTE_NR_CPF, ID_VISTORIA, DT_VISTORIA, ST_RESULTADO, ST_ESTADO_CONSERVACAO) VALUES (?, ?, ?, TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, vistoria.bicicletaNrSerie());
                instrucao.setString(2, vistoria.clienteNrCpf());
                instrucao.setInt(3, vistoria.idVistoria());
                instrucao.setString(4, vistoria.dtVistoria());
                instrucao.setString(5, vistoria.resultado());
                instrucao.setString(6, vistoria.estadoConservacao());
                instrucao.executeUpdate();
            }
        }
    }

    public void update(Vistoria vistoria) throws SQLException, ClassNotFoundException {
        classForName();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "UPDATE T_VISTORIA SET DT_VISTORIA=TO_DATE(?, 'YYYY-MM-DD'), ST_RESULTADO=?, ST_ESTADO_CONSERVACAO=? WHERE T_BICICLETA_NR_SERIE=?";
            try (PreparedStatement instrucao = con.prepareStatement(sql)) {
                instrucao.setString(1, vistoria.dtVistoria());
                instrucao.setString(2, vistoria.resultado());
                instrucao.setString(3, vistoria.estadoConservacao());
                instrucao.setString(4, vistoria.bicicletaNrSerie());
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
