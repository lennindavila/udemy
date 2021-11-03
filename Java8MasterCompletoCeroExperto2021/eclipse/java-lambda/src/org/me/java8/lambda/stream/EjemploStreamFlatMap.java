package org.me.java8.lambda.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFlatMap {

	public static void main(String[] args) {

		//El flatMap es como el filter
		Stream<Usuario> nombres = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Pepe jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.flatMap(u -> {
					if(u.getNombre().equalsIgnoreCase("Pepe")) {
						return Stream.of(u);
					}
					return Stream.empty();
				})
				.peek(System.out::println);
						
		//List<Usuario> lista = nombres.collect(Collectors.toList());
		//lista.forEach(System.out::println);
		//lista.forEach(u -> System.out.println(u));
		
		System.out.println(nombres.count());
		
		System.out.println("*******************************************************");
		
		
		
		

	}

}
