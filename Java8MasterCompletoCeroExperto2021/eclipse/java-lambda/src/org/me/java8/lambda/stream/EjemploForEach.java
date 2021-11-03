package org.me.java8.lambda.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EjemploForEach {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		lista.add("Pato");
		lista.add("Paco");
		lista.add("Pepe");
		lista.add("Papa");
		
		Stream<String> nombres = lista.stream();
		nombres.forEach(System.out::println);
		
		lista.stream().forEach(System.out::println);

	}
}
