package com.sample.entrenamiento._8_clases_anidadas;

/*Seccion: Diseno de clases
 * Teoria:
 * Clases anidadas .- 
 * 
 * 
 * */
/*
 * Inner Classes
 * pueden ser publicas, protected, default o private
 * pueden extender e implementar cualquier clase
 * pueden ser abstract o final
 * no puede declarar metodos o valores estaticos
 * puede acceder a atributos de la clase padre
 * 
 * */
class Persona{
	private String name = "Juan";
	private class Deporte{
		String deporte = "Boxeo";
		void printNombreYdeporte() {
			System.out.println("La persona " +  name + " practica el deporte " + deporte);
		}
	}
	
	void getPersonaDeporte() {
		Deporte deporte = new Deporte();
		deporte.printNombreYdeporte();
	}
}

/*
 * Local classes (clases dentro de metodos)
 * no tiene modificadores de acceso
 * no pueden ser static y no pueden tener atributos static
 * tienen acceso a todos los atributos y metodos de la clase padre
 * no tienen acceso a las variables locales a menos que sean finales
 * */

class Outter{
	int valor = 10;
	void operar() {
		int valor2 = 9;
		class Multiplicar{
			void action() {
				System.out.println("El resultado es " + valor2 * valor);
			}
		}
		Multiplicar multi = new Multiplicar();
		multi.action();
	}
}

/*
 * Anonimus inner class
 * requieren extender una clase o implementar una interfaz
 * pueden estar dentro de un metodo
 * */

class Compras{
	abstract class Ofertas{
		void ofertaDePastas() {
			System.out.println("2x1 en pastas de la abuela");
		}
		abstract void ofertasDeBebidas();					
	}
	
	Ofertas ofertasDelDia = new Ofertas() {
		@Override
		void ofertasDeBebidas() {
			System.out.println("50% de descuento en jugos");
		}
	};
}


/*
 * Static nested class
 * Puede tener cualquier modificador de acceso
 * la clase que contiene esta clase puede acceder a sus metodos o atributos sin hacer una instancia
 * */
public class Main {
	
	static class Ejemplo{
		String user = "Username";
	}
	
	
	
	public static void main(String[] args) {
		//NESTED CLASSES (INNER CLASES)
		//Persona.Deporte personaDeporte = new Persona(). new Deporte();
		//personaDeporte.printNombreYdeporte();
		new Persona().getPersonaDeporte();
		
		
		//LOCAL INNER CLASES
		Outter out = new Outter();
		out.operar();
		
		//CLASES ANONIMAS
		Compras.Ofertas ofertas = new Compras().new Ofertas() {
			@Override
			void ofertasDeBebidas() {
				System.out.println("10% de descuento en cervezas");
			}			
		};
		ofertas.ofertasDeBebidas();
		ofertas.ofertaDePastas();
		
		//STATIC INNER CLASS
		Ejemplo ejem = new Ejemplo();
		System.out.println("El user es " + ejem.user);
	}
}
