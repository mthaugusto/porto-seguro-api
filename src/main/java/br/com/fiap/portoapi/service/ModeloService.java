package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.ModeloDao;
import br.com.fiap.portoapi.model.Modelo;

public class ModeloService {
    private final ModeloDao dao = new ModeloDao();

    public List<Modelo> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Modelo findById(String bicicletaNrSerie, String clienteNrCpf) throws ClassNotFoundException {
        try {
            return dao.findById(bicicletaNrSerie, clienteNrCpf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String bicicletaNrSerie, String clienteNrCpf) throws ClassNotFoundException {
        try {
            dao.delete(bicicletaNrSerie, clienteNrCpf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Modelo modelo) throws ClassNotFoundException {
        if (!validate(modelo)) return false;
        try {
            dao.create(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Modelo modelo) throws ClassNotFoundException {
        if (!validate(modelo)) return false;
        try {
            dao.update(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(Modelo modelo) {
        if (modelo.bicicletaNrSerie().isEmpty() || modelo.bicicletaNrSerie().length() > 20) return false;
        if (modelo.clienteNrCpf().isEmpty() || modelo.clienteNrCpf().length() != 11) return false;
        if (modelo.nomeModelo().isEmpty()) return false;
        if (modelo.marcaId() <= 0) return false;

        return true;
    }
}
