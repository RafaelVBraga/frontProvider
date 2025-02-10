package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data

public class Escola  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nome;
	private Integer qntAlunos;
	
	private Endereco endereco;	
	private Set<Programa> programas;	
	private Rota rota;

}
