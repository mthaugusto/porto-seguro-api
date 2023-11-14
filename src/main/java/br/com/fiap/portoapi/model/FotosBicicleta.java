package br.com.fiap.portoapi.model;

public record FotosBicicleta(
        int idFoto, 
        String bicicletaNrSerie, 
        String clienteNrCpf, 
        byte[] foto
        ) 
    {
}