package com.santiagovalencia.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santiagovalencia.modelos.Cancion;
import com.santiagovalencia.repositorios.RepositorioCanciones;

@Service
public class ServicioCanciones {
	
	@Autowired
	private final RepositorioCanciones repositorioCanciones;
	
	public ServicioCanciones(RepositorioCanciones repositorioCanciones) {
		this.repositorioCanciones = repositorioCanciones;
	}
	
	public List<Cancion> obtenerTodasLasCanciones() {
		return this.repositorioCanciones.findAll();
	}
	
	public Cancion obtenerCancionPorId(Long id) {
		return this.repositorioCanciones.findById(id).orElse(null);
	}
	
	public Cancion agregarCancion(Cancion nuevaCancion) {
		return this.repositorioCanciones.save(nuevaCancion);
	}
	
	public Cancion actualizaCancion(Cancion cancionActual) {
		return this.repositorioCanciones.save(cancionActual);
	}
	
	public void eliminaCancion(Long idCancion) {
		this.repositorioCanciones.deleteById(idCancion);
	}
}
