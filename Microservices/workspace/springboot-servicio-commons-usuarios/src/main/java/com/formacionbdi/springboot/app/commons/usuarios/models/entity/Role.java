package com.formacionbdi.springboot.app.commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,length = 30)
	private String nombre;
	
	//El siguiente es solo ejm de como se implementa un many to many de manera bidirecional, en nuestro caso ya lo hicimos desde usuario hacia roles
	//y aqui lo hacemos de roles a usuario para que sea en dos direcciones pero solo para ejm, lo comentamos por que nuestra relacion solo sera de una direccion
	//es decir de usuario a roles. 
	//@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	//private List<Usuario> usuarios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
