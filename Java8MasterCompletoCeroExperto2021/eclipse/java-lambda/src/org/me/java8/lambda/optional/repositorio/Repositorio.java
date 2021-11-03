package org.me.java8.lambda.optional.repositorio;

import java.util.Optional;

import org.me.java8.lambda.models.Computador;

public interface Repositorio<T> {
	Optional<Computador> filtrar(String nombre);
	Optional<Computador> filtrarConLambda(String nombre);
	Computador filtrarSinOptional(String nombre);//en caso la busqueda no coincida y devuelva valor encontrado lanzara una exception
}
