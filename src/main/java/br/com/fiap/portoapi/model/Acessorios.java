package br.com.fiap.portoapi.model;

public record Acessorios(
        String velocimetro, 
        String gps, 
        String ciclocomputador,
        int acessoriosId, 
        String bicicletaNrSerie, 
        String clienteNrCpf
        ) 
    {
}
