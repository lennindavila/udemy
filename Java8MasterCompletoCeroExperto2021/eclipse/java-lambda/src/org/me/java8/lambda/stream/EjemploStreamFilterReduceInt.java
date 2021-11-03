package org.me.java8.lambda.stream;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamFilterReduceInt {

	public static void main(String[] args) {

		Stream<Integer> nombres = Stream.of(10,20,15,40,13,19,5);
		
		//Hay tres maneras de hacer la suma dentro del reduce
			//forma 1: (a,b) -> a + b
		    //forma 2: Integer::sum
			//forma 2: (a,b)-> Integer.sum(a, b)
		
		//int resultado = nombres.reduce(0,(a,b) -> a + b);
		//int resultado = nombres.reduce(0,Integer::sum);
		int resultado = nombres.reduce(0,(a,b)-> Integer.sum(a, b));
		System.out.println("" + resultado);
		
		System.out.println("*******************************************************");
	}

}
