package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterSingle {

	public static void main(String[] args) {

		Stream<Usuario> nombres = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				//.peek(System.out::println)
				.filter(u -> u.getNombre().equals("Pepa"));
				//.peek(System.out::println);
		
				
						
		Optional<Usuario> usuario = nombres.findFirst();
		//System.out.println(usuario.get());
		//System.out.println(usuario.orElse(new Usuario("default","default")).getNombre());
		//System.out.println(usuario.orElseGet(() -> new Usuario("default","default")).getNombre());
		//System.out.println(usuario.orElseThrow());
		
		
		if(usuario.isPresent()) {
			System.out.println(usuario.get());
		}else {
			System.out.println("Nada");
		}
		
		if(!usuario.isEmpty()) {// de java 11 en adelante, es mejor usar isPresent
			System.out.println(usuario.get());
		}else {
			System.out.println("Nada");
		}
		
		//lista.forEach(System.out::println);
		//lista.forEach(u -> System.out.println(u));	
		
		System.out.println("*******************************************************");
		
		
		
		

	}

}
