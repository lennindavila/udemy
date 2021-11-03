package com.sample.entrenamiento._13_builder;

/*Seccion: Principios y Patrones de Diseno
 * Teoria:
 * Patron builder .- Se utiliza como solucion cuando se esta pasando muchos parametros a un contructor de una clase
 * 
 * */


class Auto{
	String color;
	String marca;
	String modelo;
	int kilometro;
	int velocidadMaxima;
	int cantidadCombustible;	
	
	public Auto(String color, String marca, String modelo, int kilometro, int velocidadMaxima,int cantidadCombustible) {
		this.color = color;
		this.marca = marca;
		this.modelo = modelo;
		this.kilometro = kilometro;
		this.velocidadMaxima = velocidadMaxima;
		this.cantidadCombustible = cantidadCombustible;
	}	
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "Auto [color=" + color + ", marca=" + marca + ", modelo=" + modelo + ", kilometro=" + kilometro
				+ ", velocidadMaxima=" + velocidadMaxima + ", cantidadCombustible=" + cantidadCombustible + "]";
	}
	
	static class AutoBuilder{
		String color;
		String marca;
		String modelo;
		int kilometro;
		int velocidadMaxima;
		int cantidadCombustible;
		
		Auto build() {
			return new Auto(color,marca,modelo,kilometro,velocidadMaxima,cantidadCombustible);
		}

		public AutoBuilder setColor(String color) {
			this.color = color;
			return this;
		}

		public AutoBuilder setMarca(String marca) {
			this.marca = marca;
			return this;
		}

		public AutoBuilder setModelo(String modelo) {
			this.modelo = modelo;
			return this;
		}

		public AutoBuilder setKilometro(int kilometro) {
			this.kilometro = kilometro;
			return this;
		}

		public AutoBuilder setVelocidadMaxima(int velocidadMaxima) {
			this.velocidadMaxima = velocidadMaxima;
			return this;
		}

		public AutoBuilder setCantidadCombustible(int cantidadCombustible) {
			this.cantidadCombustible = cantidadCombustible;
			return this;
		}		
	}	
}




public class Main {

	public static void main(String[] args) {
		Auto autonumero1 = new Auto.AutoBuilder().setCantidadCombustible(100).setColor("Red").build();
		System.out.println(autonumero1.toString());
		autonumero1.setMarca("BMW");
		System.out.println(autonumero1.toString());
	}

}
