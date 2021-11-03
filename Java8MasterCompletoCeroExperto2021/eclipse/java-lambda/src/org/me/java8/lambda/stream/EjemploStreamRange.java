package org.me.java8.lambda.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamRange {

	public static void main(String[] args) {

		IntStream numeros = IntStream.range(5,20).peek(System.out::println);
		
		//int resultado1 = numeros.sum();
		//int resultado2 = numeros.reduce(0,Integer::sum);
		IntSummaryStatistics stats = numeros.summaryStatistics();
		
		//System.out.println("resultado1 " + resultado1);
		//System.out.println("resultado2 " + resultado2);
		System.out.println("*Stats******************************************************");
		System.out.println("stats max " + stats.getMax());
		System.out.println("stats min " + stats.getMin());
		System.out.println("stats sum " + stats.getSum());
		System.out.println("stats avg " + stats.getAverage());
		System.out.println("stats total " + stats.getCount());
		
		System.out.println("*******************************************************");
	}

}
