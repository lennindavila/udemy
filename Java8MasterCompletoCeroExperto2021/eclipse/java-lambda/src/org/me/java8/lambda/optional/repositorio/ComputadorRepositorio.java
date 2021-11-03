package org.me.java8.lambda.optional.repositorio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.me.java8.lambda.models.Computador;
import org.me.java8.lambda.models.Fabricante;
import org.me.java8.lambda.models.Procesador;

public class ComputadorRepositorio implements Repositorio<Computador> {
	private List<Computador> datasource;

	public ComputadorRepositorio() {
		datasource = new ArrayList<>();
		Procesador proc = new Procesador("I9-9880H",new Fabricante("Intel"));
		Computador asus = new Computador("Asus ROG","Strix G512");		
		asus.setProcesador(proc);
		datasource.add(asus);
		datasource.add(new Computador("MacBook Pro","MVVK2CI"));
	}

	//La version mas optimizada de este metodo es la filtrarConLambda
	@Override
	public Optional<Computador> filtrar(String nombre) {		
		for(Computador c:datasource) {
			if(c.getNombre().equalsIgnoreCase(nombre)) {
				return Optional.of(c);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<Computador> filtrarConLambda(String nombre) {
		return datasource.stream().filter(c-> c.getNombre().toLowerCase().contains(nombre.toLowerCase()))
				.findFirst();
	}	
	
	@Override
	public Computador filtrarSinOptional(String nombre) {		
		for(Computador c:datasource) {
			if(c.getNombre().equalsIgnoreCase(nombre)) {
				return c;
			}
		}
		return null;
	}


}
