package es.urjc.laliga.Jugador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.laliga.Equipo.EquipoRepository;

@Controller
public class JugadorController {
	
	@Autowired
	private JugadorRepository JugadorRepository;
	
	@Autowired
	private EquipoRepository EquipoRepository;

	@RequestMapping("/jugador/{id}")
	public String jugadorList( Model model, @PathVariable long equipo) {
		
		model.addAttribute("jugadores", EquipoRepository.findOne(equipo).getJugadores());
		
		return "jugadorList_template";
	}
	
	@RequestMapping("/jugador/detail/{jugador}")
	public String jugadorDetail( Model model, @PathVariable long jugador) {
		
		model.addAttribute("jugador", JugadorRepository.findOne(jugador));
		
		return "jugadorDetail_template";
	}
	
	@RequestMapping("/jugador/nuevo_form/{equipo}")
	public String jugadorNewForm( Model model, @PathVariable int equipo) {
		model.addAttribute("equipo", equipo);
		return "jugadorNewForm_template";
	}
	
	@PostMapping("/jugador/add")
	public String jugadorAdd( Model model, Jugador jugador, @RequestParam long equipo) {
		jugador.setEquipo( EquipoRepository.findOne(equipo) );
		JugadorRepository.save(jugador);
		model.addAttribute("equipo", jugador.getEquipo());
		model.addAttribute("nombre", jugador.getNombre());

		return "jugadorSave_template";
	}

}