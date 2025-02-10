package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data

public class Fornecedor implements Serializable{
	
	private static final long serialVersionUID = 1L;	
	private UUID id;
	private String nome;
	private String cnpj;
	private LocalDate dataRegistro;
	private String telefone;
	private String email;
	@JsonIgnoreProperties("fornecedor")
	private List<Produto> produtos; 
}
