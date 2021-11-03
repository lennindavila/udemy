package com.sample.entrenamiento._3_equals_hashcode_tostring;


/*Seccion: Diseno de clases
 * Teoria:
 * metodos toString, equals y hashcode .- 
 * 
 * 
 * */
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/*
 * Si se sobreescribe el metodo equals se recomineda tambien el sobreescribir hash
 * en el metodo has code especificamos los atributos que haran uninco
 * */


class Persona{
	String nombre;
	int dni;
	int pasos;
	
	public Persona(String nombre,int dni,int pasos) {
		this.nombre = nombre;
		this.dni = dni;
		this.pasos = pasos;
	}
	
	@Override
	public String toString() {
		return "Persona[" + "nombre :" + nombre + " dni :" + dni + " pasos:" + pasos + "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(nombre,dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null)return false;
		if (getClass() != obj.getClass()) return false;
		Persona other = (Persona) obj;
		return dni == other.dni && nombre.equals(other.nombre);
	}
}




public class Main {

	public static void main(String[] args) {
		Persona persona0 = new Persona("Lennin",42633441,10);
		//System.out.println(persona.toString());
		
		Persona persona1 = new Persona("Lennin",42633441,10);
		System.out.println(persona0.equals(persona1));
		
		//Como hemos sobre escrito el metodo hash con el campo dni y pasos, al insertar los mismos valores en la otra persona en la lista Set, 
		//no permitira su ingreso, sol se queda con la primnera coincidencia
		Set<Persona> personaSet = new HashSet<>();
		personaSet.add(persona1);
		personaSet.add(persona0);
		personaSet.stream().forEach(x -> System.out.println(x.toString()));

	}

}
