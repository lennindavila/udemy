package org.me.java8.lambda.functional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.me.java8.lambda.models.Usuario;

public class EjemploConsumer {
	//Consumer solo acepta 1 argumento, BiConsumer acepta 2 parametros
	public static void main(String[] args) {
		
		Consumer<String> consumidor0 = saludo -> System.out.println(saludo);		
		consumidor0.accept("Hi");			
		
		Consumer<String> consumidor1 = saludo -> {
			System.out.println(saludo);
		};		
		consumidor1.accept("Hello");		
		
		Consumer<Date> consumidor2 = fecha -> {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(fecha);
		};		
		consumidor2.accept(new Date());	
		
		Consumer<String> consumidor3 = System.out::println;		
		consumidor3.accept("usando metodo referencia, sin lambda");		
		
		List<String> nombres = Arrays.asList("Pedro","Pablo","Vilma");
		nombres.forEach(consumidor3);
		
		BiConsumer<String,Integer> biConsumer1 = (nombre,edad)-> {
			System.out.println(nombre + ", tiene " + edad + " anos!");
		};		
		biConsumer1.accept("Lennin", 18);		
		
		BiConsumer<String,Integer> biConsumer2 = (nombre,edad)-> System.out.println(nombre + ", tiene " + edad + " anos!");				
		biConsumer2.accept("Pilar", 18);		
		
		Usuario usuario = new Usuario();
		BiConsumer<Usuario, String> asignarNombre = Usuario::setNombre;
		asignarNombre.accept(usuario, "Lennin");
		System.out.println("Nombre usuario: " + usuario.getNombre());
		
		Supplier<String> proveedor1 = () -> "Hola mundo Supplier en una linea";		
		System.out.println(proveedor1.get());		
		
		Supplier<String> proveedor2 = () -> {
			return "Hola mundo Supplier";
		};
		System.out.println(proveedor2.get());
		
		Supplier<Usuario> creaUsuario = Usuario::new;
		Usuario usuario1 = creaUsuario.get();
		BiConsumer<Usuario, String> asignarNombre2 = Usuario::setNombre;
		asignarNombre2.accept(usuario1, "Lennin usu");
		System.out.println("Nombre usuario: " + usuario1.getNombre());
		
		
	}

}
