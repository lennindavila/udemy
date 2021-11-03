package com.sample.entrenamiento._1_herencia_composicion;


class Persona{
	String name;
}

class Trabajador extends Persona{
	String empresa;
	public void render() {
		System.out.println("Nombre : " + empresa  + " Trabaja en : " + this.name );
	}
}

class Skills extends Persona{
	String habilidades = "java";
	Trabajador trabajador = new Trabajador();
	
	public void render() {
		trabajador.empresa = "Pepsi";
		name = "Guille";
		trabajador.render();
		System.out.println("Nombre : " + name + " ,empresa : " + trabajador.empresa + " sus habilidades son " + habilidades);
	}
	
}

public class Main {

	public static void main(String[] args) {
		Skills skills = new Skills();
		skills.render();

	}

}
