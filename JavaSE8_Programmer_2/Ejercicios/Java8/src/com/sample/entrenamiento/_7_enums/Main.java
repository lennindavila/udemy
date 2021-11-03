package com.sample.entrenamiento._7_enums;

/*Seccion: Diseno de clases
 * Teoria:
 * Enumeradores .- 
 * 
 * 
 * */

/*
 * Enums
 * se puede usar en un switch
 * no se pueden extender pero si pueden implementar interfaces
 * son key sensitive
 * tiene nombre y ordinal
 * 
 * */

interface Juegos{
	String jugar();
}

enum Temporada implements Juegos{
	VERANO("HACE CALOR"),
	INVIERNO("HACE FRIO");
	
	String descripcion;
	Temporada(String s){
		this.descripcion = s;
	}	

	String getDescripcion() {
		return descripcion;
	}

	@Override
	public String jugar() {
		return "Voler en playa";
	}
}

enum Sexo implements Juegos{
	MACHO{
		String getDescripcion() {
			return "HACE CALOR";
		}

		@Override
		public String jugar() {
			// TODO Auto-generated method stub
			return "futbol en playa";
		}
	},
	HEMBRA{
		String getDescripcion() {
			return "HACE FRIO"; 
		}

		@Override
		public String jugar() {
			// TODO Auto-generated method stub
			return "futbol en pista";
		}
	};
	
	String getDescripcion() {
		return "";
	}
}

public class Main {

	public static void main(String[] args) {
		System.out.println(Temporada.VERANO.getDescripcion());
		System.out.println(Temporada.VERANO.jugar());
		
		System.out.println(Sexo.MACHO.getDescripcion());
		System.out.println(Sexo.MACHO.jugar());
		
		System.out.println(Sexo.HEMBRA.getDescripcion());
		System.out.println(Sexo.HEMBRA.jugar());
	}

}
