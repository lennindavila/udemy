package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>  {
	
	public Usuario findByUsername(String username);
	
	@Query("Select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
}
