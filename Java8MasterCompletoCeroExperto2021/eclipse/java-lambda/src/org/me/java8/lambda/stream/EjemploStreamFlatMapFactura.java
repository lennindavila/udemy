package org.me.java8.lambda.stream;

import java.util.Arrays;
import java.util.List;

import org.me.java8.lambda.models.Factura;
import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFlatMapFactura {

	public static void main(String[] args) {
		Usuario u1 = new Usuario("Jhom", "Doe");
		Usuario u2 = new Usuario("Pepe", "Perez");

		u1.addFactura(new Factura("compras tecnologias"));
		u1.addFactura(new Factura("compras muebles"));

		u2.addFactura(new Factura("bicicleta"));
		u2.addFactura(new Factura("notebok gamer"));

		List<Usuario> usuarios = Arrays.asList(u1, u2);

		// Modo 1 Api Stream
		
		usuarios.stream()
			.flatMap(u -> u.getFacturas().stream())
			.forEach(
				f -> System.out.println(f.getDescripcion().concat(" : Cliente ").concat(f.getUsuario().toString())));
		
		
		// Modo 2 Api Stream
		/*
		usuarios.stream()
			.map(Usuario::getFacturas)
			.flatMap(List::stream)
			.forEach(
				f -> System.out.println(f.getDescripcion().concat(" : Cliente ").concat(f.getUsuario().toString())));
		*/

		// Modo Clasico
		/*
		 * for(Usuario u: usuarios) { for(Factura f: u.getFacturas()) {
		 * System.out.println(f.getDescripcion()); } }
		 */

	}

}
