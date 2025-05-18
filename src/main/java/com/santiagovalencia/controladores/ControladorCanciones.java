package com.santiagovalencia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.santiagovalencia.modelos.Artista;
import com.santiagovalencia.modelos.Cancion;
import com.santiagovalencia.servicios.ServicioArtistas;
import com.santiagovalencia.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/canciones")
public class ControladorCanciones {
	
	@Autowired
	private final ServicioCanciones servicioCanciones;
	private final ServicioArtistas servicioArtistas;
	
	public ControladorCanciones(ServicioCanciones servicioCanciones,
								ServicioArtistas servicioArtistas) {
		this.servicioCanciones = servicioCanciones;
		this.servicioArtistas = servicioArtistas;
	}
	
	@GetMapping("")
	public String desplegarCanciones(Model modelo) {
		List<Cancion> listaCanciones = this.servicioCanciones.obtenerTodasLasCanciones();
		modelo.addAttribute("listaCanciones", listaCanciones);
		return "canciones.jsp";
	}
	
	@GetMapping("/detalle/{idCancion}")
	public String desplegarDetalleCancion(Model modelo,
										   @PathVariable("idCancion") Long idCancion) {
		Cancion cancionActual = this.servicioCanciones.obtenerCancionPorId(idCancion);
		modelo.addAttribute("cancionActual", cancionActual);
		return "detalleCancion.jsp";
	}
	
	@GetMapping("/formulario/agregar")
	public String formularioAgregarCancion(@ModelAttribute("cancion") Cancion cancion,
										   Model modelo) {
		List<Artista> listaArtistas = servicioArtistas.obtenerTodosLosArtistas();
		modelo.addAttribute("listaArtistas", listaArtistas);
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/procesa/agregar")
	public String procesarAgregarCancion(@Valid @ModelAttribute("cancion") Cancion nuevaCancion,
										 BindingResult validaciones,
										 @RequestParam("id_artista") Long id_artista) {
		if(validaciones.hasErrors()) {
			return "agregarCancion.jsp";
		}
		Artista artistaActual = servicioArtistas.obtenerArtistaPorId(id_artista);
		nuevaCancion.setArtista(artistaActual);
		this.servicioCanciones.agregarCancion(nuevaCancion);
		return "redirect:/canciones";
	}
	
	@GetMapping("/formulario/editar/{idCancion}")
	public String formularioEditarCancion(@ModelAttribute("cancion") Cancion cancion,
										  @PathVariable("idCancion") Long idCancion,
										  Model modelo) {
		Cancion cancionActual = this.servicioCanciones.obtenerCancionPorId(idCancion);
		List<Artista> listaArtistas = servicioArtistas.obtenerTodosLosArtistas();
		modelo.addAttribute("cancion", cancionActual);
		modelo.addAttribute("listaArtistas", listaArtistas);
		
		return "editarCancion.jsp";
	}
	
	@PutMapping("/procesa/editar/{idCancion}")
	public String procesarEditarCancion(@Valid @ModelAttribute("cancion") Cancion cancion,
										BindingResult validaciones,
										@PathVariable("idCancion") Long idCancion,
										@RequestParam("id_artista") Long id_artista) {
		if(validaciones.hasErrors()) {
			return "editarCancion.jsp";
		}
		Artista artistaActual = servicioArtistas.obtenerArtistaPorId(id_artista);
		cancion.setArtista(artistaActual);
		cancion.setId(idCancion);
		this.servicioCanciones.actualizaCancion(cancion);
		return "redirect:/canciones";
	}
	
	@DeleteMapping("/eliminar/{idCancion}")
	public String procesarEliminarCancion(@PathVariable("idCancion") Long idCancion) {
		this.servicioCanciones.eliminaCancion(idCancion);
		
		return "redirect:/canciones";
	}
}
