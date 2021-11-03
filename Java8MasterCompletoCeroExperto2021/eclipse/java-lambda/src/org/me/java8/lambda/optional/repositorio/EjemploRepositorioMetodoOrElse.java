package org.me.java8.lambda.optional.repositorio;

import java.util.Optional;

import org.me.java8.lambda.models.Computador;

public class EjemploRepositorioMetodoOrElse {

	public static void main(String[] args) {
		Repositorio<Computador> repositorio = new ComputadorRepositorio();
		
		//El metodo orElse consume mas recursos por que siempre se ejecuta la parte else, en cambio orElseGet solo se ejecuta cuando la condicional es false
		//Computador defecto = new Computador("HP Omen","LA0001");
		Computador pc = repositorio.filtrarConLambda("rog").orElse(valorDefecto());
		System.out.println(pc);
		
		//El metodo orElseGet es mas eficiente, mas optimo que orElse
		pc = repositorio.filtrarConLambda("macbook").orElseGet(()->  valorDefecto() );
		System.out.println(pc);
	}
	
	public static Computador valorDefecto() {
		System.out.println("Obteniendo valor por defecto!!!");
		return new Computador("HP Omen","LADefault");
	}

}
