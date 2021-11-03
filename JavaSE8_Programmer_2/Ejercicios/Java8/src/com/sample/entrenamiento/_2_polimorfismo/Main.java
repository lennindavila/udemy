package com.sample.entrenamiento._2_polimorfismo;


/*Seccion: Diseno de clases
 * Teoria:
 * Polimorfismo por interfaces .- 
 * 
 * 
 * */
/*
 * Polimorfismo es implementar en dos objectos diferentes la misma funcion pero con comportamiento diferente.
 * Aqui haremos ejemplo de Polimorfismo a traves de interface, en el ejemplo de abstractclass veremos a polimorfismo a traves de clase abstracta
 * */

interface Peleador{
	public default void entrenar() {
		System.out.println("Peleador entrena");
	}
	
	public default void ingresar() {
		System.out.println("Ingresa al ring");
	}
	
	void pelear();
	
	default void salir() {
		System.out.println("Sale del ring");
	}
}


class Boxeador implements Peleador{
	
	@Override
	public void pelear() {
		System.out.println("Pelea con las manos");
	}
}

class Karateca implements Peleador{
	@Override
	public void pelear() {
		System.out.println("Pelea con manos y piernas");
	}
}


public class Main {

	public static void main(String[] args) {
		Boxeador boxeador = new Boxeador();
		boxeador.entrenar();
		boxeador.ingresar();
		boxeador.pelear();
		boxeador.salir();
		
		Karateca karateca = new Karateca();
		karateca.entrenar();
		karateca.ingresar();
		karateca.pelear();		
		karateca.salir();
	}

}
