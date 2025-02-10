package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class Rota implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nome;
	
	private Set<Escola> escolas;
}
