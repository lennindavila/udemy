package com.formacionbdi.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;

@RepositoryRestResource(path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {
	
	/*
	 * Desde Postman se puede acceder mediante este request:
	 * http://localhost:8090/api/usuarios/usuarios/search/findByUsername?username=lennin
	 * */
	//public Usuario findByUsername(String username);
	
	/*Aca lo personalizamos si no deseamos que se pueda acceder desde postman con el nombre original del metodo, usaremos @RestResource
	 * Desde Postman se puede acceder mediante este request:
	 * http://localhost:8090/api/usuarios/usuarios/search/buscar-username?nombre=lennin
	 * */
	@RestResource(path="buscar-username")
	public Usuario findByUsername(@Param("username") String username);

	//http://localhost:8090/api/usuarios/usuarios/search/obtenerPorUsername?username=lennin
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(String username);
}
