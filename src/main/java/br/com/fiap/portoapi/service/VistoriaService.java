package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.fiap.portoapi.data.VistoriaDao;
import br.com.fiap.portoapi.model.Vistoria;

public class VistoriaService {
    private final VistoriaDao dao = new VistoriaDao();

    public List<Vistoria> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vistoria findBySerieNr(String bicicletaNrSerie) throws ClassNotFoundException {
        try {
            return dao.findBySerieNr(bicicletaNrSerie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Vistoria vistoria) throws ClassNotFoundException {
        try {
            dao.delete(vistoria.bicicletaNrSerie());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Vistoria vistoria) throws ClassNotFoundException {
        if (!validate(vistoria)) return false;
        try {
            dao.create(vistoria);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean update(Vistoria vistoria) throws ClassNotFoundException {
        if (!validate(vistoria)) return false;
        try {
            dao.update(vistoria);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean validate(Vistoria vistoria) {
        if (vistoria.bicicletaNrSerie().isEmpty() || vistoria.bicicletaNrSerie().length() > 20) return false;
        if (vistoria.clienteNrCpf().isEmpty() || vistoria.clienteNrCpf().length() != 11) return false;
        if (vistoria.idVistoria() <= 0) return false;
        if (vistoria.dtVistoria() == null) return false;

        return true;
    }
}
