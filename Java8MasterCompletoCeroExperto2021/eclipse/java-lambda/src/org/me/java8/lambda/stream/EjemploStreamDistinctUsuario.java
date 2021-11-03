package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamDistinctUsuario {

	public static void main(String[] args) {

		Stream<Usuario> nombres = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones","Paco gonzales","Paco gonzales")
				//.distinct() //En este lugar funciona sin necesidad de crear el metodo equals en la clase Usuario 
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.distinct(); //En este lugar para que funcione se debe implementar el metodo equals en la clase Usuario
				
						
		nombres.forEach(System.out::println);
		System.out.println("*******************************************************");

	}

}
