package org.ldavila.anotaciones.ejemplo;


import java.time.LocalDate;

import org.ldavila.anotaciones.ejemplo.models.Producto;
import org.ldavila.anotaciones.ejemplo.procesador.JsonSerializador;

public class EjemploAnotacion {

	public static void main(String[] args) {
		Producto p = new Producto();
		p.setFecha(LocalDate.now());
		p.setNombre("mesa centro ROBLE");
		p.setPrecio(1000L);
		
		System.out.println("json = " + JsonSerializador.convertirJson(p));
	}

}
