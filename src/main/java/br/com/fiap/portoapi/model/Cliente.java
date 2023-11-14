package br.com.fiap.portoapi.model;

import java.util.Date;

public record Cliente(
		String nrCpf, 
		String nomeCliente, 
		String emailCliente,
		String nrTelefone, 
		String enderecoCliente, 
		char generoCliente,
		String tipoCliente, 
		double renda, 
		String estadoCivil,
		String dtNascimento, 
		String dtCadastro
		) 
	{
}