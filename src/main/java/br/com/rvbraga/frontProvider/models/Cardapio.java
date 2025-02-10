package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Cardapio  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private List<Refeicao> refeicoes;
	

}
