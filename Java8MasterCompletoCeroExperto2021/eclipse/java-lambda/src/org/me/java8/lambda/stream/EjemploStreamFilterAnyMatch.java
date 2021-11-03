package org.me.java8.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterAnyMatch {

	public static void main(String[] args) {

		boolean existe = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.anyMatch(u -> u.getId().equals(7));
				
		
		System.out.println(existe);			
		System.out.println("*******************************************************");
		
		//Las lineas de arriba en la forma antigua seria
		/*List<Usuario> lista = Arrays.asList(new Usuario("Pato0","Patazo0"),
				new Usuario("Pato1","Patazo1"),
				new Usuario("Pato2","Patazo2"),
				new Usuario("Pato3","Patazo3"),
				new Usuario("Pato4","Patazo4"));
		
		boolean resultado = false;
		for(Usuario u:lista) {
			if(u.getId().equals(3)) {
				resultado = true;
				break;
			}
		}*/
		
	}
}
