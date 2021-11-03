package com.sample.entrenamiento._12_singleton;


/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Patron singleton .- Cuando queremos que no se cree mas que una sola instancia de una clase
 * 
 * */


class Conexion{
	String url;
	String token;
	
	private static Conexion conexion;
	
	public Conexion(String url,String token) {
		this.url =url;
		this.token =token;
	}	
	
	public String getUrl() {
		return url;
	}

	public static Conexion getInstance(String url,String token) {
		if(conexion == null) {
			conexion = new Conexion(url,token);
		}
		return conexion;
	}	
}

public class Main {

	public static void main(String[] args) {
		Conexion user1 = Conexion.getInstance("endpoint1", "123");
		Conexion user2 = Conexion.getInstance("endpoint12", "123456");
		
		System.out.println("USER 1 ENDPOINT " + user1.getUrl() + " USER 2 ENDPOINT " + user2.getUrl());	

	}

}
