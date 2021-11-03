package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterSingle2 {

	public static void main(String[] args) {
		
		//orElseGet es mas seguro que solo .get ya que permite manipular el valor q se devuelve en caso el filter no encuentre nada

		Usuario usuario = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				//.peek(System.out::println)
				.filter(u -> u.getId().equals(2))
				.findFirst()
				//.orElse(null);
				//.get();
				.orElseGet(() -> new Usuario("default","default"));
		
		System.out.println(usuario);			
		System.out.println("*******************************************************");			
	}
}
