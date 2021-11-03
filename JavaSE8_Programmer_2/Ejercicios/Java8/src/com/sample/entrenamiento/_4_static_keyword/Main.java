package com.sample.entrenamiento._4_static_keyword;


/*Seccion: Diseno de clases
 * Teoria:
 * static keyword.- 
 * 
 * 
 * */
/*
 *El keyword Static se aplica a clase, metodo, variable y bloque {}
 * , no se puede aplicar a constructores
 * */

class OutterClass{
	static int valor = 10;
	
	public static int print() {
		return valor;
	}
	
	class Inner{
		int valor = 7;
	}
	
	{
		System.out.println("Invocando clase OutterClass");
	}
	
	public OutterClass() {
		System.out.println("Contructor OutterClass invocado");
	}
	
	static class InnerStatic{
		void display() {
			System.out.println("void display en InnerStatic");
		}
	}
}




public class Main {

	public static void main(String[] args) {
		new OutterClass();
		System.out.println(OutterClass.valor);
		OutterClass.Inner inner = new OutterClass().new Inner();
		System.out.println(inner.valor);
		
		OutterClass.InnerStatic instatic = new OutterClass.InnerStatic();
		instatic.display();
	}

}
