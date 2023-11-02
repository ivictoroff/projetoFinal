package com.projetobasico.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Cliente {
	
	@Id @GeneratedValue
	private Long Id;
	private String Nome;
	private String telefone;
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
