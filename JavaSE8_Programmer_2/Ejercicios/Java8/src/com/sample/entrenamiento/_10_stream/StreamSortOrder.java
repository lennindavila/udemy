package com.sample.entrenamiento._10_stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamSortOrder {
	
	
	public void OrdenamientoNumeros() {		
		List<Integer> listaInt = Arrays.asList(10,23,-4,0,18);
		System.out.println("******Asc**********");
		listaInt.stream().sorted().forEach(System.out::println);
		System.out.println("******Desc**********");
		listaInt.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
		
		System.out.println("******List<> ASC**********");
		List<Integer> resultAsc = listaInt.stream().sorted().collect(Collectors.toList());
		resultAsc.forEach(n -> {
			System.out.println(n);
		});
		
		System.out.println("******List<> DESC**********");
		List<Integer> resultDesc = listaInt.stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
		resultDesc.forEach(n -> {
			System.out.println(n);
		});
	}

	public void OrdenamienString() {
		List<String> lista = Arrays.asList("z", "m", "a", "b", "x");
		
		lista.stream().sorted().forEach(System.out::println);
		System.out.println("***********");
		lista.stream().sorted(Collections.reverseOrder()).forEach(System.out::println);
	}
	
	public void OrdenamienPersona() {
		ArrayList<Persona> listaPersona = new ArrayList<>();
		listaPersona.add(new Persona(1,"Aennin"));
		listaPersona.add(new Persona(1,"Caria"));
		listaPersona.add(new Persona(2,"Filar"));
		listaPersona.add(new Persona(2,"Zesica"));
		listaPersona.add(new Persona(3,"Yamy"));
		listaPersona.add(new Persona(4,"Samy"));
		listaPersona.add(new Persona(0,"Angel"));
		
		System.out.println("****Name DESC y ASC*******");
		listaPersona.stream().map(p -> p.getName()).sorted(Collections.reverseOrder()).forEach(p -> System.out.println(p.toString()));
		System.out.println("***********");
		listaPersona.stream().map(p -> p.getName()).sorted().forEach(p -> System.out.println(p.toString()));
		
		System.out.println("****ID DESC y ASC*******");
		listaPersona.stream().map(p -> p.getId()).sorted(Collections.reverseOrder()).forEach(p -> System.out.println(p.toString()));
		System.out.println("***********");
		listaPersona.stream().map(p -> p.getId()).sorted().forEach(p -> System.out.println(p.toString()));
	}
	
	public static void main(String[] args) {
		StreamSortOrder obj = new StreamSortOrder();
		obj.OrdenamientoNumeros();
		obj.OrdenamienString();
		obj.OrdenamienPersona();
	}

}
