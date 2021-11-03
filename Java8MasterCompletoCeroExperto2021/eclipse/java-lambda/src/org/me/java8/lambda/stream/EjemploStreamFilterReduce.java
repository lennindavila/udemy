package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterReduce {

	public static void main(String[] args) {

		Stream<String> nombres = Stream.of("nombres apellido","Paco gonzales","Paco gonzales","Paco gonzales","Pepe mujica","Papa jhones")
			.distinct();
			
			//nombres.forEach(System.out::println);
			
		String resultado = nombres.reduce("Resultado concatenacion ",(a,b) -> a + " # " + b);
		System.out.println("" + resultado);
		
		System.out.println("*******************************************************");
		
		
		
		

	}

}
