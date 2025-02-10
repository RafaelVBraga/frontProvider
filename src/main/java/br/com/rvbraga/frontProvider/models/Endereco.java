package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data

public class Endereco  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String CEP;
	private String Cidade;
	private String pontoReferencia;	
	private Escola escola;
}
