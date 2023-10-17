package com.desafio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.modelo.Noticias;
import com.desafio.response.NoticiasResponseRest;
import com.desafio.service.INoticiasService;
@CrossOrigin
@RestController
@RequestMapping("/apiDesafio")
public class NoticiasRestController {

	@Autowired
	private INoticiasService service;
	
	@GetMapping("/noticias")
	public ResponseEntity<NoticiasResponseRest> buscarNoticias(){
		
		ResponseEntity<NoticiasResponseRest> response = service.buscarNoticias();
		return response;
	}
	
	@GetMapping("/buscarNoticia/{id}")
	public ResponseEntity<NoticiasResponseRest> consultaPorId(@PathVariable Long id){
		ResponseEntity<NoticiasResponseRest> response = service.buscarPorId(id);
		return response;
		
	}
	@PostMapping("/guardaNoticia")
	public ResponseEntity<NoticiasResponseRest> guardar(@RequestBody Noticias request){
		ResponseEntity<NoticiasResponseRest> response = service.guardar(request);
		return response;
		
	}
	@DeleteMapping("/eliminarNoticia/{id}")
	public ResponseEntity<NoticiasResponseRest> eliminar(@PathVariable Long id){
		ResponseEntity<NoticiasResponseRest> response = service.eliminar(id);
		return response;
		
	}
	
	
}
