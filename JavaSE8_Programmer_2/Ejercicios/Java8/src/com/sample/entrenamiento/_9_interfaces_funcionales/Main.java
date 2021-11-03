package com.sample.entrenamiento._9_interfaces_funcionales;

/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Interfaces funcionales .- 
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

	static void clasificar(Animal animal, ClasificadorDeAnimales clasificador) {
		if(clasificador.chequear(animal)) {
			System.out.println(animal.toString());
		}
	}
	
	public static void main(String[] args) {
		clasificar(new Animal("Perro",false,true), animal -> animal.puedeSaltar);
		clasificar(new Animal("Pez",true,false), animal -> animal.puedeNadar);
		
	}
}
