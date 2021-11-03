package com.cjava.expert.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cjava.expert.model.Curso;

public interface CursoRepository extends MongoRepository<Curso, String>{

}
