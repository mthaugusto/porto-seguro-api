package br.com.fiap.portoapi.model;

import java.util.Date;

public record Bicicleta(
        String clienteNrCpf, 
        String nrSerie, 
        int anoFabricacao,
        String dtCompra,
        double vlAvaliado, 
        double vlFabrica,
        String situacao, 
        String tipo
        ) 
    {
}