package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.AcessoriosDao;
import br.com.fiap.portoapi.model.Acessorios;

public class AcessoriosService {
    private final AcessoriosDao dao = new AcessoriosDao();

    public List<Acessorios> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Acessorios findById(int id) throws ClassNotFoundException {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Acessorios acessorios) throws ClassNotFoundException {
        try {
            dao.delete(acessorios);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Acessorios acessorios) throws ClassNotFoundException {
        if (!validate(acessorios)) return false;
        try {
            dao.create(acessorios);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Acessorios acessorios) throws ClassNotFoundException {
        if (!validate(acessorios)) return false;
        try {
            dao.update(acessorios);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(Acessorios acessorios) {
        if (acessorios.velocimetro().isEmpty() || acessorios.velocimetro().length() != 1)
            return false;
        if (acessorios.gps().isEmpty() || acessorios.gps().length() != 1)
            return false;
        if (acessorios.ciclocomputador().isEmpty() || acessorios.ciclocomputador().length() != 1)
            return false;
        if (acessorios.acessoriosId() <= 0)
            return false;
        if (acessorios.bicicletaNrSerie().isEmpty() || acessorios.bicicletaNrSerie().length() > 20)
            return false;
        if (acessorios.clienteNrCpf().isEmpty() || acessorios.clienteNrCpf().length() != 11)
            return false;

        return true;
    }
}
