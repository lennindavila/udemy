package com.cjava.expert.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjava.expert.model.Curso;
import com.cjava.expert.repository.CursoRepository;

@RestController
@RequestMapping(value = "/api")
public class CursoController {
	
	@Autowired
	CursoRepository repo;
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()	{
		return "{ \"todoOk\" : true }";
	}
	
	@GetMapping("/cursos")
	public List<Curso> listarCursos()	{
		return repo.findAll();
	}
	@PostMapping("/curso")
	public Curso grabarCurso(@RequestBody Curso curso)	{
		repo.insert(curso);
		return curso;
	}
}
