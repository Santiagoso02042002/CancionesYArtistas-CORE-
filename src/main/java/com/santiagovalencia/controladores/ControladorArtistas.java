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
import org.springframework.web.bind.annotation.RequestMapping;

import com.santiagovalencia.modelos.Artista;
import com.santiagovalencia.servicios.ServicioArtistas;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/artistas")
public class ControladorArtistas {
	
	@Autowired
	private final ServicioArtistas servicioArtistas;
	
	public ControladorArtistas(ServicioArtistas servicioArtistas) {
		this.servicioArtistas = servicioArtistas;
	}
	
	@GetMapping("")
	public String desplegarArtistas(Model modelo) {
		List<Artista> listaArtistas = this.servicioArtistas.obtenerTodosLosArtistas();
		modelo.addAttribute("listaArtistas", listaArtistas);
		return "artistas.jsp";
	}
	
	@GetMapping("/detalle/{idArtista}")
	public String desplegarDetalleArtista(Model modelo,
										  @PathVariable("idArtista") Long idArtista) {
		Artista artistaActual = this.servicioArtistas.obtenerArtistaPorId(idArtista);
		modelo.addAttribute("artistaActual", artistaActual);
		return "detalleArtista.jsp";
	}
	
	@GetMapping("/formulario/agregar")
	public String formularioAgregarArtista(@ModelAttribute("artista") Artista artista) {
		return "agregarArtista.jsp";
	}
	
	@PostMapping("/procesa/agregar")
	public String procesaAgregarArtista(@Valid @ModelAttribute("artista") Artista nuevoArtista,
										BindingResult validaciones) {
		if(validaciones.hasErrors()) {
			return "agregarArtista.jsp";
		}
		this.servicioArtistas.agregarArtista(nuevoArtista);
		return "redirect:/artistas";
	}
}
