package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.MarcaDao;
import br.com.fiap.portoapi.model.Marca;

public class MarcaService {
    private final MarcaDao dao = new MarcaDao();

    public List<Marca> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Marca findById(int id) throws ClassNotFoundException {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Marca marca) throws ClassNotFoundException {
        try {
            dao.delete(marca);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Marca marca) throws ClassNotFoundException {
        if (!validate(marca)) return false;
        try {
            dao.create(marca);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Marca marca) throws ClassNotFoundException {
        if (!validate(marca)) return false;
        try {
            dao.update(marca);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(Marca marca) {
        if (marca.nomeMarca().isEmpty()) return false;
        if (marca.paisOrigem().isEmpty()) return false;
        if (marca.marcaId() <= 0) return false;

        return true;
    }
}
