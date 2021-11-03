package com.sample.entrenamiento._11_encapsulamiento_inmutabilidad;


/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Encapsulamiento e inmutabilidad .- Es una interfaz funcional, recibe un argumento de tipo generic y devuelve un valor true or false
 * inmutabilidad se da mas cuando se maneja hilos.
 * 
 * */

class Casa{
	private int precio;
	private String barrio;
	
	public Casa(int precio,String barrio) {
		this.precio = precio;
		this.barrio = barrio;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
}


public class Main {

	public static void main(String[] args) {
		Casa casa1 = new Casa(1000,"Centro");
		Casa casa2 = new Casa(1500,"Sur");
		casa1.setBarrio("privado");
		System.out.println("La casa1 esta en barrio " + casa1.getBarrio() + " y la casa2 esta en " + casa2.getBarrio());
		
		String saludo = "Hola";
		saludo = saludo.toUpperCase();
		StringBuilder saludar = new StringBuilder("Hello");
		saludar.append(" word");
		System.out.println(saludo);		
		System.out.println(saludar);

	}

}
