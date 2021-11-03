package org.ldavila.anotaciones.ejemplo.procesador;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

import org.ldavila.anotaciones.ejemplo.Init;
import org.ldavila.anotaciones.ejemplo.JsonAtributo;
import org.ldavila.anotaciones.ejemplo.exception.JsonSerializadorException;

import com.sun.jdi.InvocationException;

public class JsonSerializador {
	
	public static void inicializarObjeto(Object object) {
		if(object == null) {
			throw new JsonSerializadorException("El objeto a serializar no puede ser null!");			
		}
		
		Method[]  metodos = object.getClass().getDeclaredMethods();
		Arrays.stream(metodos)
				.filter(m -> m.isAnnotationPresent(Init.class))
				.forEach(m -> {
					m.setAccessible(true);
					try {
						m.invoke(object);
					}catch(IllegalAccessException | InvocationTargetException e) {
						throw new JsonSerializadorException("Error al serializar, no se puede inicializar el objeto " + e.getMessage());
					}
				});
	}
	
	public static String convertirJson(Object object) {
		
		//if(Objects.isNull(object)) {
		if(object == null) {
			throw new JsonSerializadorException("El objeto a serializar no puede ser null!");			
		}
		
		inicializarObjeto(object);
		
		Field[] atributos = object.getClass().getDeclaredFields();
		return Arrays.stream(atributos)
				.filter(f -> f.isAnnotationPresent(JsonAtributo.class))
				.map(f -> {
							f.setAccessible(true);//en false saldra error por que los atributos son private
							String nombre = f.getAnnotation(JsonAtributo.class).nombre().equals("") 
									? f.getName()
									: f.getAnnotation(JsonAtributo.class).nombre();
							try {
								//Aplicamos la capitalizacion del texto segun el atributo capitalizar se encuentra en true.
								Object valor = f.get(object);
								if(f.getAnnotation(JsonAtributo.class).capitalizar() && valor instanceof String) {
									String nuevoValor = (String) valor;
									//tres maneras de convertir la primera letra en mayuscula, con substring y la otra con charAt y finalmente con stream
									//La tercera forma convierte a mayuscula todas las primeras letra de cada palabra del atributo con capitalizar = true
									//nuevoValor=nuevoValor.substring(0,1).toUpperCase() + nuevoValor.substring(1).toLowerCase();
									//nuevoValor= String.valueOf(nuevoValor.charAt(0)).toUpperCase() + nuevoValor.substring(1).toLowerCase();
									nuevoValor= Arrays.stream(nuevoValor.split(" "))
														.map(palabra -> palabra.substring(0,1).toUpperCase() + palabra.substring(1).toLowerCase())
														.collect(Collectors.joining(" "));
									f.set(object,nuevoValor);
								}
								return "\"" + nombre + "\":\"" + f.get(object) + "\"";
								// return "" + nombre + ":" + f.get(p) + "";
							} catch (IllegalAccessException e) {
								throw new JsonSerializadorException("Error al serializar el json: " + e.getMessage());
							}

				})
				.reduce("{", (a, b) -> {
										if ("{".equals(a)) {
											return a + b;
										}
										return a + ", " + b;
				}).concat("}");
		
	}
}
