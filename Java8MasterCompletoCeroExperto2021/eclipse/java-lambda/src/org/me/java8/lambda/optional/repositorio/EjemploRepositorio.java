package org.me.java8.lambda.optional.repositorio;

import java.util.Optional;

import org.me.java8.lambda.models.Computador;

public class EjemploRepositorio {

	public static void main(String[] args) {
		Repositorio<Computador> repositorio = new ComputadorRepositorio();
		
		//Java8
		Optional<Computador> pcOptional = repositorio.filtrar("asus");
		//se valida el null con el metodo isPresent
		if(pcOptional.isPresent()) {
			System.out.println(pcOptional.get());
		}else {
			System.out.println("No se encontro");
		}
		//Java >= 9 Aca en una sola linea se valida el nulo
		repositorio.filtrar("asus").ifPresentOrElse(System.out::println,()-> System.out.println("No se encontro"));
		
		//Java >= 9 Aca en una sola linea se valida el nulo y busca como en un like %%
		repositorio.filtrarConLambda("asus").ifPresentOrElse(System.out::println,()-> System.out.println("No se encontro"));
		
		
		//********************************
		//Esta lanza error al ser sin optional causado por devolver nulo, es necesario validar en el if de abajo
		Computador pc = repositorio.filtrarSinOptional("asus");
		
		//Este if es para validar el null que puede causar la exception
		if(pc!=null) {
			System.out.println(pc.getNombre());
		}else {
			System.out.println("No se encontro");
		}
	}

}
