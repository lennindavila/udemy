package org.me.java8.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class EjemploStream {
	public static void main(String[] args) {
		Stream<String> nombres = Stream.of("nombres0","Paco","Pepe","Papa");
		
		//nombres.forEach(e -> System.out.println(e));
		nombres.forEach(System.out::println);
		
		String[] arr = {"nombres1","Paco","Pepe","Papa"};
		
		
		
		nombres = Arrays.stream(arr);
		nombres.forEach(System.out::println);
		
		Stream<String> nombres2 = Stream.<String>builder()
				.add("nombres2")
				.add("Pato")
				.add("Paco")
				.build();
		
		nombres2.forEach(System.out::println);
		
		List<String> lista = new ArrayList<>();
		lista.add("nombres3");
		lista.add("Paco");
		lista.add("Pepe");
		lista.add("Papa");
		
		lista.forEach(System.out::println);
		
		Stream<String> nombres3 = lista.stream();
		nombres3.forEach(System.out::println);
		

	}
}
