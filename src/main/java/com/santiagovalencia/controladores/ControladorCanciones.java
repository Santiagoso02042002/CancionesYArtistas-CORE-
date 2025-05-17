package com.santiagovalencia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.santiagovalencia.modelos.Cancion;
import com.santiagovalencia.servicios.ServicioCanciones;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/canciones")
public class ControladorCanciones {
	
	@Autowired
	private final ServicioCanciones servicioCanciones;
	
	public ControladorCanciones(ServicioCanciones servicioCanciones) {
		this.servicioCanciones = servicioCanciones;
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
	public String formularioAgregarCancion(@ModelAttribute("cancion") Cancion cancion) {
		return "agregarCancion.jsp";
	}
	
	@PostMapping("/procesa/agregar")
	public String procesarAgregarCancion(@Valid @ModelAttribute("cancion") Cancion nuevaCancion,
										 BindingResult validaciones) {
		if(validaciones.hasErrors()) {
			return "agregarCancion.jsp";
		}
		this.servicioCanciones.agregarCancion(nuevaCancion);
		return "redirect:/canciones";
	}
	
	@GetMapping("/formulario/editar/{idCancion}")
	public String formularioEditarCancion(@ModelAttribute("cancion") Cancion cancion,
										  @PathVariable("idCancion") Long idCancion,
										  Model modelo) {
		Cancion cancionActual = this.servicioCanciones.obtenerCancionPorId(idCancion);
		modelo.addAttribute("cancion", cancionActual);
		
		return "editarCancion.jsp";
	}
	
	@PutMapping("/procesa/editar/{idCancion}")
	public String procesarEditarCancion(@Valid @ModelAttribute("cancion") Cancion cancion,
										BindingResult validaciones,
										@PathVariable("idCancion") Long idCancion) {
		if(validaciones.hasErrors()) {
			return "editarCancion.jsp";
		}
		
		cancion.setId(idCancion);
		
		this.servicioCanciones.actualizaCancion(cancion);
		
		return "redirect:/canciones";
	}
}
