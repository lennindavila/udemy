package org.me.java8.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamListToStream {

	public static void main(String[] args) {
		List<Usuario> lista = new ArrayList<>();
		lista.add(new Usuario("Pedro","Picapiedra"));
		lista.add(new Usuario("Vilma","Picapiedra"));
		lista.add(new Usuario("Pebbles","Picapiedra"));
		lista.add(new Usuario("Pablo","Marmol"));
		lista.add(new Usuario("Betty","Marmol"));
		lista.add(new Usuario("BanBan","Marmol"));
		lista.add(new Usuario("Pedro","Picapiedra2"));

		Stream<String> nombres = lista.stream().map(u -> u.getNombre().toUpperCase()
											 .concat(" ")
											 .concat(u.getApellido().toUpperCase())
				).flatMap(nombre -> {
					if(nombre.contains("Pedro".toUpperCase())) {
						return Stream.of(nombre);
					}
					return Stream.empty();
				})
		.map(String::toLowerCase)
		.peek(System.out::println);
		
		System.out.println(nombres.count());
		

	}

}
