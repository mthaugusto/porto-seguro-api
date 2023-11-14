package br.com.fiap.portoapi.service;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.portoapi.data.ClienteDao;
import br.com.fiap.portoapi.model.Cliente;

public class ClienteService {
    ClienteDao dao = new ClienteDao();

    public List<Cliente> findAll() throws ClassNotFoundException {
        try {
            return dao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Cliente findByCpf(String nrCpf) throws ClassNotFoundException {
        try {
            return dao.findByCpf(nrCpf);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(Cliente cliente) throws ClassNotFoundException {
        try {
            dao.delete(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean create(Cliente cliente) throws ClassNotFoundException {
        if (!validate(cliente)) return false;
        try {
            dao.create(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Cliente cliente) throws ClassNotFoundException {
        if (!validate(cliente)) return false;
        try {
            dao.update(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean validate(Cliente cliente) {
        if (cliente.nrCpf().isEmpty() || cliente.nrCpf().length() != 11) return false;
        if (cliente.nomeCliente().isEmpty()) return false;
        if (cliente.emailCliente().isEmpty()) return false;
        if (cliente.nrTelefone().isEmpty()) return false;
        if (cliente.enderecoCliente().isEmpty()) return false;
        if (cliente.generoCliente() != 'M' && cliente.generoCliente() != 'F') return false;
        if (!cliente.tipoCliente().equals("PF") && !cliente.tipoCliente().equals("PJ")) return false;
        if (cliente.renda() <= 0) return false;
        if (cliente.estadoCivil().isEmpty()) return false;

        return true;
    }
}
