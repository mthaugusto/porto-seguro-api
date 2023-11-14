package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.BicicletaDao;
import br.com.fiap.portoapi.model.Bicicleta;

public class BicicletaService {
    private final BicicletaDao dao = new BicicletaDao();

    public List<Bicicleta> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bicicleta findByNrSerie(String nrSerie) throws ClassNotFoundException {
        try {
            return dao.findByNrSerie(nrSerie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Bicicleta bicicleta) throws ClassNotFoundException {
        try {
            dao.delete(bicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Bicicleta bicicleta) throws ClassNotFoundException {
        if (!validate(bicicleta)) return false;
        try {
            dao.create(bicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Bicicleta bicicleta) throws ClassNotFoundException {
        if (!validate(bicicleta)) return false;
        try {
            dao.update(bicicleta);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(Bicicleta bicicleta) {
        if (bicicleta.clienteNrCpf().isEmpty() || bicicleta.clienteNrCpf().length() != 11) return false;
        if (bicicleta.nrSerie().isEmpty() || bicicleta.nrSerie().length() > 20) return false;
        if (bicicleta.anoFabricacao() <= 1980) return false;
        if (bicicleta.dtCompra() == null) return false;
        if (bicicleta.vlAvaliado() <= 0) return false;
        if (bicicleta.vlFabrica() <= 0) return false;
        if (bicicleta.situacao().isEmpty() || bicicleta.situacao().length() > 20) return false;
        if (bicicleta.tipo().isEmpty() || bicicleta.tipo().length() > 20) return false;

        return true;
    }
}
