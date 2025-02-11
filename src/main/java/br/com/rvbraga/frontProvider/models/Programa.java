package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data

public class Programa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	
	private String nome;
	
    private Set<Escola> escolas;
	
	//Esse atributo guardará informação a percapita de todos os produtos	
	private HashMap<String, Float> percapita;	
	//Esse atributo guardará informação a frequencia de todos os produtos	
	private HashMap<String, Float> frequencia;
	

}
