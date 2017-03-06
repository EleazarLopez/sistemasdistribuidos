package es.urjc.laliga.Sancion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.laliga.Jugador.JugadorRepository;
import es.urjc.laliga.Temporada.TemporadaRepository;

@Controller
public class SancionController {
	
	@Autowired
	private JugadorRepository JugadorRepository;
	
	@Autowired
	private TemporadaRepository TemporadaRepository;
	
	@Autowired
	private SancionRepository SancionRepository;

	@RequestMapping("/sancion/{temporada}")
	public String sancionList( Model model, @PathVariable long temporada) {
		
		model.addAttribute("sanciones", SancionRepository.findByTemporada(TemporadaRepository.findOne(temporada)));
		
		return "sancionList_template";
	}
	
	@RequestMapping("/sancion/detail/{sancion}")
	public String sancionDetail( Model model, @PathVariable long sancion) {
		
		model.addAttribute("sancion", SancionRepository.findOne(sancion));
		
		return "sancionDetail_template";
	}
	
	@RequestMapping("/sancion/nuevo_form/{temporada}")
	public String sancionNewForm( Model model, @PathVariable long temporada) {
		model.addAttribute("temporada", temporada);
		model.addAttribute("jugadores", JugadorRepository.findByEquipo_Temporada(TemporadaRepository.findOne(temporada)));
		return "sancionNewForm_template";
	}
	
	@PostMapping("/sancion/add")
	public String sancionAdd( Model model, Sancion sancion, @RequestParam long temporada, @RequestParam long jugador) {
		sancion.setTemporada( TemporadaRepository.findOne(temporada) );
		sancion.setJugador( JugadorRepository.findOne(jugador) );
		SancionRepository.save(sancion);
		model.addAttribute("sancion", sancion);

		return "sancionSave_template";
	}

}