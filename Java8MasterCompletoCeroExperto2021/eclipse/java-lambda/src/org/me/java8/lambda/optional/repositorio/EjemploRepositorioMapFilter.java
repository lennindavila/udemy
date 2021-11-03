package org.me.java8.lambda.optional.repositorio;

import java.util.Optional;

import org.me.java8.lambda.models.Computador;
import org.me.java8.lambda.models.Fabricante;
import org.me.java8.lambda.models.Procesador;

public class EjemploRepositorioMapFilter {

	public static void main(String[] args) {
		Repositorio<Computador> repositorio = new ComputadorRepositorio();		
		
		Fabricante f1 = repositorio.filtrarConLambda("rog")
				.map(c -> c.getProcesador())
				.map(p -> p.getFabricante())
				.orElseThrow();
		System.out.println(f1.getNombre());		
		
		//Lo mismo escrito de otra con referencia de metodos, con macbook sale error
		Fabricante f2 = repositorio.filtrarConLambda("rog")
				.map(Computador::getProcesador)
				.map(Procesador::getFabricante)
				.orElseThrow();
		System.out.println(f2.getNombre());			
		
		//Haciendo algunos ajustes, validando nulo y devolviendo string
		String f3 = repositorio.filtrarConLambda("asus")
				.map(Computador::getProcesador)
				.map(Procesador::getFabricante)
				.map(Fabricante::getNombre)
				.orElse("Desconocido");
		System.out.println(f3);		
		
		//Haciendo algunos ajustes, validando nulo y devolviendo string y filtrando por AMD
		String f4 = repositorio.filtrarConLambda("asus")
				.map(Computador::getProcesador)
				.map(Procesador::getFabricante)
				.filter(fab -> "AMD".equalsIgnoreCase(fab.getNombre()))
				.map(Fabricante::getNombre)
				.orElse("Desconocido");
		System.out.println(f4);
		
		//Haciendo uso de flatmap
		String f5 = repositorio.filtrarConLambda("asus")
				.flatMap(Computador::getProcesadorOpt)
				.flatMap(Procesador::getFabricanteOpt)
				.filter(fab -> "intel".equalsIgnoreCase(fab.getNombre()))
				.map(Fabricante::getNombre)
				.orElse("Desconocido");
		System.out.println(f5);	
	}
}
