package br.com.fiap.portoapi.model;

import java.util.Date;

public record Vistoria(
        String bicicletaNrSerie, 
        String clienteNrCpf, 
        int idVistoria,
        String dtVistoria, 
        String resultado, 
        String estadoConservacao
        ) 
    {
}
