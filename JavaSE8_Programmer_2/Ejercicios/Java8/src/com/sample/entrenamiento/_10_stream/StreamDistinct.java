package com.sample.entrenamiento._10_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDistinct {

	public static void main(String[] args) {
		ArrayList<Persona> lista = new ArrayList<>();
		lista.add(new Persona(1,"Lennin"));
		lista.add(new Persona(1,"maria"));
		lista.add(new Persona(2,"pilar"));
		lista.add(new Persona(2,"jesica"));
		lista.add(new Persona(3,"Samy"));
		lista.add(new Persona(4,"Samy"));
		
		Stream<Persona> res = lista.stream().filter(distinctBy(p -> p.getId()));
		res.forEach(System.out::println);
		

	}	
	
	public static <T> Predicate<T> distinctBy(Function<? super T,?> key){
		Set<Object> var = ConcurrentHashMap.newKeySet();
		return v -> var.add(key.apply(v));
	}
}
