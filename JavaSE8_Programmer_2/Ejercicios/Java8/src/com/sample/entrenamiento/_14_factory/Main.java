package com.sample.entrenamiento._14_factory;


/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Patron factory .- Recibe un tipo de argumento para determinar si retorno tipo A o B.
 * 
 * */

abstract class Luchador{
	abstract void luchar();
}

class Boxeador extends Luchador{
	@Override
	void luchar() {
		System.out.println("Pelea con manitas");
	}
}

class Karateca extends Luchador{
	@Override
	void luchar() {
		System.out.println("Pelea con pies y manos");
	}
}

class LuchadorFactory{
	Luchador getLuchador(String tipo) {
		if(tipo.equalsIgnoreCase("Boxeador")) {
			return new Boxeador();
		}
		if(tipo.equalsIgnoreCase("Karateca")) {
			return new Karateca();
		}
		return null;
	}
}

public class Main {

	public static void main(String[] args) {
		LuchadorFactory factory = new LuchadorFactory();
		Luchador boxeador = factory.getLuchador("Boxeador");
		boxeador.luchar();
		
		Luchador karateca = factory.getLuchador("Karateca");
		karateca.luchar();

	}

}
