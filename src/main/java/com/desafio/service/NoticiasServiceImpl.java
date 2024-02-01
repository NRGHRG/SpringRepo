package com.desafio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.modelo.Noticias;
import com.desafio.modelo.dao.INoticiasDao;
import com.desafio.response.NoticiasResponseRest;

@Service
public class NoticiasServiceImpl implements INoticiasService {
	private static final Logger log = LoggerFactory.getLogger(NoticiasServiceImpl.class);

	@Autowired
	private INoticiasDao noticiasDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<NoticiasResponseRest> buscarNoticias() {
		log.info("inicio metodo buscarNoticia()");
		NoticiasResponseRest response = new NoticiasResponseRest();
		try {
			List<Noticias> noticias = (List<Noticias>) noticiasDao.findAll();

			response.getNoticiasResponse().setNoticias(noticias);
			response.setMetada("Respuesta ok", "200", "Respuesta exitosa");

		} catch (Exception e) {

			response.setMetada("Respuesta no ok", "-1", "Respuesta incorrecta");

			log.error("error al consultar usuarios: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoticiasResponseRest> buscarPorId(Long id) {
		log.info("Inicio método buscarPorId");
		
		NoticiasResponseRest response = new NoticiasResponseRest();
		List<Noticias> list = new ArrayList<>();
		try {
			
			Optional<Noticias> noticias = noticiasDao.findById(id);
			
			if (noticias.isPresent()) {
				list.add(noticias.get());
				response.getNoticiasResponse().setNoticia(noticias.get());
				
			} else {
				log.error("Noticia no encontrada");
				response.setMetada("respuesta no OK", "-1", "Noticia no encontrada");
				return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			log.error("Error en consultar Noticia");
			response.setMetada("respuesta no OK", "-1", "Noticia no encontrada");
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.setMetada("Respuesta ok", "200", "Respuesta exitosa");
		return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);  // devuelve 200
		
	}

	@Override
	@Transactional
	public ResponseEntity<NoticiasResponseRest> guardar(Noticias noticias) {
	    log.info("Inicio método guardar noticia");

	    NoticiasResponseRest response = new NoticiasResponseRest();
	    List<Noticias> list = new ArrayList<>();

	    try {
	        noticiasDao.save(noticias);
	        list.add(noticias);
	        response.getNoticiasResponse().setNoticias(list);
	    } catch (Exception e) {
	        log.error("Error al guardar noticia", e);
	        response.setMetada("Respuesta no OK", "-1", "Error al guardar noticia");
	        return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    response.setMetada("Respuesta ok", "200", "Noticia guardada");
	    return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
	}


	@Override
	public ResponseEntity<NoticiasResponseRest> eliminar(Long id) {
			log.info("Inicio método eliminar noticia");
		
			NoticiasResponseRest response = new NoticiasResponseRest();
		
		
		try {
			
			
			noticiasDao.deleteById(id);
			response.setMetada("Respuesta ok", "200", "Noticia eliminada");
			
		}catch (Exception e) {
			log.error("Error en eliminar la noticia", e.getMessage());
			e.getStackTrace();
			response.setMetada("respuesta no OK", "-1", "Noticia no eliminada");
	
			return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<NoticiasResponseRest>(response, HttpStatus.OK);
		
	
	}

}
