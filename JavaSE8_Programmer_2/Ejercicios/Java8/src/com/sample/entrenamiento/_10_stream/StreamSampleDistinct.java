package com.sample.entrenamiento._10_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class StreamSampleDistinct {
	
	
	public void distinctPersona() {
		ArrayList<Persona> lista = new ArrayList<>();
		lista.add(new Persona(1,"Lennin"));
		lista.add(new Persona(1,"maria"));
		lista.add(new Persona(2,"pilar"));
		lista.add(new Persona(2,"jesica"));
		lista.add(new Persona(3,"Samy"));
		lista.add(new Persona(4,"Samy"));
		
		
		lista.stream().filter(dis(p->p.getId())).forEach(p -> System.out.println("OO " + p.toString()));
		
		lista.stream().filter(distinto(Persona::getId)).forEach(p -> System.out.println("KK" + p.toString()));
		
		
		List<Persona> per = lista.stream().
			map((Persona p) -> {
				p.setName(p.getName().toUpperCase());
				return p;
			})			
			.collect(Collectors.toList());
		
		per.forEach(System.out::println);
		
		//System.out.println(per.get(0).getName());
		
		//lista.stream().filter(distinct(Persona::getId)).forEach((Persona p) -> System.out.println("KK " + p.toString()));
		
		
		//List<Persona> result = lista.stream().filter(distinctBy(Persona::getName)).filter(distinctBy(Persona::getId)).collect(Collectors.toList());
		
		
		
		//result.forEach(p -> System.out.println(p.toString()));
	}
	
	public static <T> Predicate<T> dis(Function<? super T, ?> key){
		Set<Object> var = ConcurrentHashMap.newKeySet();
		return v -> var.add(key.apply(v));
	}
	

	public static <T>Predicate<T> distinto(Function<? super T, ?> val){
		Set<Object> var = ConcurrentHashMap.newKeySet();
		return v -> var.add(val.apply(v));
	}
	
	

	public static void main(String[] args) {
		StreamSampleDistinct obj = new StreamSampleDistinct();
		obj.distinctPersona();			
	}
	
	public static <T>Predicate<T> distinct(Function<? super T,?> key){
		Set<Object> var = ConcurrentHashMap.newKeySet();
		return v -> var.add(key.apply(v));
	}


	
}
