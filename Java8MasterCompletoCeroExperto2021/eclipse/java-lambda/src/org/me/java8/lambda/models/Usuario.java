package org.me.java8.lambda.models;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String nombre;
	private String apellido;
	private Integer id;
	private static int ultimoId;
	
	private List<Factura> facturas;
	
	public Usuario() {		
	}
	
	public Usuario(String nombre,String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = ++ultimoId;
		this.facturas = new ArrayList<>();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		return nombre + " " + apellido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public static int getUltimoId() {
		return ultimoId;
	}

	public static void setUltimoId(int ultimoId) {
		ultimoId = ultimoId;
	}	

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void addFactura(Factura factura) {
		this.facturas.add(factura);
		factura.setUsuario(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
}
