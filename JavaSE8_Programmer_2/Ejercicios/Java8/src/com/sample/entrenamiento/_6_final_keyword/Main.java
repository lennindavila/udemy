package com.sample.entrenamiento._6_final_keyword;


/*Seccion: Diseno de clases
 * Teoria:
 * final keyword .- 
 * 
 * 
 * */
/*
 * El keyword final se aplica a clase, metodo, variable, no se puede aplicar a constructores
 * 
 * 
 * 
 * */

public class Main {
	final static int IVA = 10;
	
	final class  Outter{
		String name = "Est";
		public final  void print() {
			System.out.println("print in Outter");
		}
	}
	
	class Inner {
		Outter outter = new Outter();
		public void test() {
			outter.print();
			outter.name = "ad";
		}
	}
	
	public static void main(String[] args) {
		//System.out.println(valor);
		new Main().new Inner().test();
	}

}
