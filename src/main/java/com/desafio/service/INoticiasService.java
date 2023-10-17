package com.desafio.service;

import org.springframework.http.ResponseEntity;

import com.desafio.modelo.Noticias;
import com.desafio.response.NoticiasResponseRest;



public interface INoticiasService {
public ResponseEntity<NoticiasResponseRest> buscarNoticias();
public ResponseEntity<NoticiasResponseRest> buscarPorId(Long id);
public ResponseEntity<NoticiasResponseRest> guardar(Noticias noticias);
public ResponseEntity<NoticiasResponseRest> eliminar(Long id);

}
