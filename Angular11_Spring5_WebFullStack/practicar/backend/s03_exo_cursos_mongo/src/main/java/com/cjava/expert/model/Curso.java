package com.cjava.expert.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "curso")
public class Curso {
	@Id 
	private  String id;
	private  String nombre;
	private Integer creditos;
	
	
	public Curso(String id, String nombre, Integer creditos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.creditos = creditos;
	}
	
	
	public Curso() {
		super();
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getCreditos() {
		return creditos;
	}
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	

	
	
}