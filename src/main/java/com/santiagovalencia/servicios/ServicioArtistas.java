package com.santiagovalencia.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiagovalencia.modelos.Artista;
import com.santiagovalencia.repositorios.RepositorioArtistas;

@Service
public class ServicioArtistas {
	
	@Autowired
	private final RepositorioArtistas repositorioArtistas;
	
	public ServicioArtistas(RepositorioArtistas repositorioArtistas) {
		this.repositorioArtistas = repositorioArtistas;
	}
	
	public List<Artista> obtenerTodosLosArtistas() {
		return this.repositorioArtistas.findAll();
	}
	
	public Artista obtenerArtistaPorId(Long id) {
		return this.repositorioArtistas.findById(id).orElse(null);
	}
	
	public Artista agregarArtista(Artista nuevoArtista) {
		return this.repositorioArtistas.save(nuevoArtista);
	}
}
