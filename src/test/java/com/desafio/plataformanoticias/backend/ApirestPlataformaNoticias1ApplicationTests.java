package com.desafio.plataformanoticias.backend;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.desafio.modelo.dao.INoticiasDao;
import com.desafio.response.NoticiasResponseRest;
import com.desafio.service.NoticiasServiceImpl;
import com.desafio.modelo.Noticias;

@ExtendWith(MockitoExtension.class)
public class ApirestPlataformaNoticias1ApplicationTests {

    @Mock
    private INoticiasDao noticiasDao;

    @InjectMocks
    private NoticiasServiceImpl noticiasService;

    @Test
    public void testBuscarNoticias() {
        List<Noticias> noticiasList = new ArrayList<>();
        when(noticiasDao.findAll()).thenReturn(noticiasList);

        ResponseEntity<NoticiasResponseRest> result = noticiasService.buscarNoticias();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(noticiasList, result.getBody().getNoticiasResponse().getNoticias());
    }

    @Test
    public void testBuscarPorIdNoticiaExistente() {
        Long id = 1L;
        Noticias noticia = new Noticias();
        Optional<Noticias> optionalNoticia = Optional.of(noticia);
        when(noticiasDao.findById(id)).thenReturn(optionalNoticia);

        ResponseEntity<NoticiasResponseRest> result = noticiasService.buscarPorId(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(noticia, result.getBody().getNoticiasResponse().getNoticia());
    }

    @Test
    public void testBuscarPorIdNoticiaNoExistente() {
        Long id = 1L;
        Optional<Noticias> optionalNoticia = Optional.empty();
        when(noticiasDao.findById(id)).thenReturn(optionalNoticia);

        ResponseEntity<NoticiasResponseRest> result = noticiasService.buscarPorId(id);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void testGuardarNoticia() {
        Noticias noticia = new Noticias();
        when(noticiasDao.save(any(Noticias.class))).thenReturn(noticia);

        ResponseEntity<NoticiasResponseRest> result = noticiasService.guardar(noticia);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(noticia, result.getBody().getNoticiasResponse().getNoticias().get(0));
    }

    @Test
    public void testEliminarNoticia() {
        Long id = 1L;

        ResponseEntity<NoticiasResponseRest> result = noticiasService.eliminar(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
