package org.me.java8.lambda.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilter {

	public static void main(String[] args) {

		Stream<Usuario> nombres = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				//.peek(System.out::println)
				.filter(u -> u.getNombre().equals("Pepe"));
				//.peek(System.out::println);
		
				
						
		List<Usuario> lista = nombres.collect(Collectors.toList());
		lista.forEach(System.out::println);
		//lista.forEach(u -> System.out.println(u));	
		
		System.out.println("*******************************************************");
		
		
		
		

	}

}
