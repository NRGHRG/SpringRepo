package com.desafio.response;

import java.util.List;

import com.desafio.modelo.Noticias;


public class NoticiasResponse {
	
	private Noticias noticia;
	private List<Noticias> noticias;

	public List<Noticias> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticias> noticias) {
		this.noticias = noticias;
	}

	public Noticias getNoticia() {
		return noticia;
	}

	public void setNoticia(Noticias noticia) {
		this.noticia = noticia;
	}

}
