package br.com.fiap.portoapi.model;

public record Modelo(
    String bicicletaNrSerie, 
    String clienteNrCpf, 
    String nomeModelo, 
    int marcaId
        ) 
    {
}
