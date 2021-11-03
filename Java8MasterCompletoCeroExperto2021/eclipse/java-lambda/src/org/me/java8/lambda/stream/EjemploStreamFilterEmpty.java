package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterEmpty {

	public static void main(String[] args) {

		long count = Stream.of("nombres apellido","","Paco gonzales","Pepe mujica","")
				.filter(String::isEmpty)
				.peek(System.out::println)
				.count();
				
		System.out.println("count " + count);
								
		
		
		System.out.println("*******************************************************");
		
		
		
		

	}

}
