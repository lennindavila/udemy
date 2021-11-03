package com.sample.entrenamiento._5_abstractclass;


/*Seccion: Diseno de clases
 * Teoria:
 * clases y metodos abstractos.- aplicaremos polimorfismo a traves de las clases abstractas
 * Polimorfismo es implementar en dos objectos diferentes la misma funcion pero con comportamiento diferente.
 * 
 * */
abstract class Peleador{
		
	void ingresar() {
		System.out.println("Ingresa al ring");
	}
	
	abstract void pelear();
	
	void salir() {
		System.out.println("Sale del ring");
	}
}


class Boxeador extends Peleador{
	
	@Override
	void pelear() {
		System.out.println("Pelea con las manos");
	}
}

class Karateca extends Peleador{
	
	//Sobreescritura
	@Override
	void pelear() {
		System.out.println("Pelea con manos y piernas");
	}
	
	
	//Sobrecarga / overload
	void pelear(int a) {
		System.out.println("int " + a);
	}

	//Sobrecarga / overload	
	void pelear(String a) {
		System.out.println("String " + a);
	}	
}


public class Main {

	public static void main(String[] args) {
		Boxeador boxeador = new Boxeador();
		boxeador.ingresar();
		boxeador.pelear();
		boxeador.salir();
		
		
		Karateca karateca = new Karateca();
		karateca.ingresar();
		karateca.pelear();		
		karateca.salir();
		karateca.pelear(1);
		karateca.pelear("wao");
	}

}
