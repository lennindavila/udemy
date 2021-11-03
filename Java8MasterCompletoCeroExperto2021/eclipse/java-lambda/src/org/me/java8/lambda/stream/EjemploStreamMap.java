package org.me.java8.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.me.java8.lambda.models.Usuario;

public class EjemploStreamMap {
	
	//map = permite modificar los items de la lista
	//peek = permite hacer siguiemiento a los valores de la lista
	
	public static void main(String[] args) {
		Stream.of("nombres0","Paco","Pepe","Papa").map(nombre -> {return nombre.toUpperCase();} ).forEach(System.out::println);
		
		Stream<String> nombres1 = Stream.of("nombres1","Paco","Pepe","Papa")
				.map(nombre -> {
					return nombre.toUpperCase();
					})
				.peek(e -> System.out.println(e))
				.map(nombre -> {
					return nombre.toLowerCase();
					});				
		nombres1.forEach(System.out::println);
		
		System.out.println("*******************************************************");
		
		Stream<Usuario> nombres2 = Stream.of("usuario2","Paco","Pepe","Papa")
				.map(nombre -> 
					new Usuario(nombre,null)
					)
				.peek(e -> System.out.println(e))
				.map(usuario -> {
					String nombre = usuario.getNombre().toLowerCase();
					usuario.setNombre(nombre);
					return usuario;
					});	
		
		List<Usuario> lista = nombres2.collect(Collectors.toList());
		lista.forEach(System.out::println);
		//nombres2.forEach(System.out::println);
		System.out.println("*******************************************************");
		
		Stream<String> nombres3 = Stream.of("nombres3","Paco","Pepe","Papa")
				.map(String::toUpperCase)
				.peek(System.out::println)
				.map(String::toLowerCase)
				.peek(System.out::println);
		
		List<String> lista2 = nombres3.collect(Collectors.toList());
		lista2.forEach(System.out::println);
		
		System.out.println("*******************************************************");
		
		Stream<Usuario> nombres4 = Stream.of("nombres4 apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.peek(usuario -> System.out.println(usuario.getNombre()))
				.map(usuario -> {
					String nombre = usuario.getNombre().toUpperCase();
					usuario.setNombre(nombre);
					return usuario;
				} );
						
		List<Usuario> listau = nombres4.collect(Collectors.toList());
		listau.forEach(u -> System.out.println(u.getApellido()));
		
		System.out.println("*******************************************************");
		
		Stream<Usuario> nombres5 = Stream.of("nombres5 apellido","Paco gonzales","Pepe mujica","Papa jhones")
				.map( nombre -> new Usuario(nombre.split(" ")[0],nombre.split(" ")[1]))
				.peek(System.out::println)
				.map(usuario -> {
					String nombre = usuario.getNombre().toUpperCase();
					usuario.setNombre(nombre);
					return usuario;
				} );
						
		List<Usuario> listaux = nombres5.collect(Collectors.toList());
		listaux.forEach(u -> System.out.println(u));		

	}
}
