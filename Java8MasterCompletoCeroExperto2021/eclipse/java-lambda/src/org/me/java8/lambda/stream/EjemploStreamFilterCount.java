package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterCount {

	public static void main(String[] args) {

		long count = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.peek(System.out::println)
				.count();
				
				
		
		System.out.println(count);			
		System.out.println("*******************************************************");			
	}
}
