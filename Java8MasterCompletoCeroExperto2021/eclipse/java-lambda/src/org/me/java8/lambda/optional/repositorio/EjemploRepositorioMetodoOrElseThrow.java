package org.me.java8.lambda.optional.repositorio;

import java.util.Optional;

import org.me.java8.lambda.models.Computador;

public class EjemploRepositorioMetodoOrElseThrow {

	public static void main(String[] args) {
		Repositorio<Computador> repositorio = new ComputadorRepositorio();
		
		//metodo get funciona igual que orElseThrow, aunque se recomienda usar orElseThrow por ser mas eficiente y optimo, ademas que get puede ser deprecated
		Computador pcget = repositorio.filtrarConLambda("rog").get();
		System.out.println(pcget);
		
		//recomendado usar orElseThrow en lugar de get 
		Computador pcthrow = repositorio.filtrarConLambda("rog").orElseThrow();
		System.out.println(pcthrow);
		
		//lanza por default NoSuchElementException, pero lo podemos personalizar, tenemos dos maneras de hacerlo a continuacion
		Computador pcthrowex1 = repositorio.filtrarConLambda("rog").orElseThrow(IllegalStateException::new);//con referencia de metodo
		System.out.println(pcthrowex1);
		
		Computador pcthrowex2 = repositorio.filtrarConLambda("rog").orElseThrow(()-> new IllegalStateException());//con lambda
		System.out.println(pcthrowex2);
		
		//Otro ejemplo con map filter
		String archivo = "documento.pdf";
		String extension = Optional.ofNullable(archivo)
				.filter(a -> a.contains("."))
				.map(a -> a.substring(archivo.lastIndexOf(".") + 1))
				.orElseThrow();
		System.out.println(extension);
		
		
		
	}

}
