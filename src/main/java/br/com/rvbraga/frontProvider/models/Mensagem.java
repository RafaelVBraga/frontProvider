package br.com.rvbraga.frontProvider.models;

import lombok.Data;

@Data
public class Mensagem {
	private String tipo;
	private String texto;
	public Mensagem(String tipo, String texto){
		this.tipo = tipo;
		this.texto = texto;
	}
}
