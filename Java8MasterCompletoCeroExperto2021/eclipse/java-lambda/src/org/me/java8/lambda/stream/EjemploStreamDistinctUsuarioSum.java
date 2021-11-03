package org.me.java8.lambda.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamDistinctUsuarioSum {

	public static void main(String[] args) {

		IntStream largoNombres = Stream.of("nombres apellido","Paco gonzales","Pepe mujica","Papa jhones","Paco gonzales","Paco gonzales")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.distinct() //En este lugar para que funcione se debe implementar el metodo equals en la clase Usuario
				.mapToInt(u -> u.toString().length())//Suma el total de caracteres excluyendo los duplicados gracias al distinct de arriba
				.peek(System.out::println);
				
			
		
		//largoNombres.forEach(System.out::println);
		//System.out.println("Largo nombres: " + largoNombres.sum());
		
		IntSummaryStatistics stats = largoNombres.summaryStatistics();
		System.out.println("*Stats******************************************************");
		System.out.println("stats max " + stats.getMax());
		System.out.println("stats min " + stats.getMin());
		System.out.println("stats sum " + stats.getSum());
		System.out.println("stats avg " + stats.getAverage());
		System.out.println("stats total " + stats.getCount());
		
		System.out.println("*******************************************************");

	}

}
