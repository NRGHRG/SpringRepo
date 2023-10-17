package com.desafio.modelo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.desafio.modelo.Noticias;

@Repository
public interface INoticiasDao extends CrudRepository<Noticias, Long> {

}
