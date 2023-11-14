package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.FotosBicicletaDao;
import br.com.fiap.portoapi.model.FotosBicicleta;

public class FotosBicicletaService {
    private final FotosBicicletaDao dao = new FotosBicicletaDao();

    public List<FotosBicicleta> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public FotosBicicleta findById(int id) throws ClassNotFoundException {
        try {
            return dao.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(FotosBicicleta fotosBicicleta) throws ClassNotFoundException {
        try {
            dao.delete(fotosBicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(FotosBicicleta fotosBicicleta) throws ClassNotFoundException {
        if (!validate(fotosBicicleta)) return false;
        try {
            dao.create(fotosBicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(FotosBicicleta fotosBicicleta) throws ClassNotFoundException {
        if (!validate(fotosBicicleta)) return false;
        try {
            dao.update(fotosBicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(FotosBicicleta fotosBicicleta) {
        if (fotosBicicleta.idFoto() <= 0)
            return false;
        if (fotosBicicleta.bicicletaNrSerie().isEmpty() || fotosBicicleta.bicicletaNrSerie().length() > 20)
            return false;
        if (fotosBicicleta.clienteNrCpf().isEmpty()
                || fotosBicicleta.clienteNrCpf().length() != 11)
            return false;
        if (fotosBicicleta.foto() == null || fotosBicicleta.foto().length == 0)
            return false;

        return true;
    }
}
