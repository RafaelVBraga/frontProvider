package br.com.rvbraga.frontProvider.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@Data
public class Produto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String nome;
	private String unidade_medida;
	private float ajuste_medida;
	private LocalDate validade;	
	@JsonIgnoreProperties("produtos")
    private Fornecedor fornecedor;
	
}
