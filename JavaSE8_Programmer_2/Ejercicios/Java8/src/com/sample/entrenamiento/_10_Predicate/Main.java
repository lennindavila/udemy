package com.sample.entrenamiento._10_Predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Predicate .- Es una interfaz funcional, recibe un argumento de tipo generic y devuelve un valor true or false
 * 
 * 
 * */


@FunctionalInterface
interface interface1{
	void metodo1();
	default void metodo2() {
		
	}
}

@FunctionalInterface
interface interface2 extends interface1{
	
}

class Animal{
	String specie;
	boolean puedeNadar;
	boolean puedeSaltar;
	
	public Animal(String specie,boolean puedeNadar,boolean puedeSaltar) {
		this.specie = specie;
		this.puedeNadar = puedeNadar;
		this.puedeSaltar = puedeSaltar;
	}

	@Override
	public String toString() {
		return "Animal [specie=" + specie + ", puedeNadar=" + puedeNadar + ", puedeSaltar=" + puedeSaltar + "]";
	}	
}

interface ClasificadorDeAnimales{
	boolean chequear(Animal animal);
}

public class Main {

	static void clasificar(Animal animal, Predicate<Animal> clasificador) {
		if(clasificador.test(animal)) {
			System.out.println(animal.toString());
		}
	}
	
	public static void main(String[] args) {
		clasificar(new Animal("Perro",false,true), animal -> {
			return animal.puedeSaltar;
		});
		clasificar(new Animal("Pez",true,false), animal -> {
			return animal.puedeNadar;
		});
		
		Predicate<Integer> filtrarMayores = x -> x > 2;
		List<Integer> ints = Arrays.asList(1,2,3,4,5,6,7,8,9);
		
		//ints.stream().filter(filtrarMayores).forEach(x -> System.out.println(x));
		
		//Otra manera, agregamos otro filtro como metodo anonimo
		ints.stream().filter(filtrarMayores).filter(new Predicate<Integer>() {
			@Override
			public boolean test(Integer integer) {
				return integer < 6;
			}
			}).forEach(x -> System.out.println(x));
		
	}
}