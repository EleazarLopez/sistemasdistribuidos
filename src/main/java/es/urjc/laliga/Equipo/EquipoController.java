package es.urjc.laliga.Equipo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.laliga.Temporada.TemporadaRepository;

@Controller
public class EquipoController {
	
	@Autowired
	private EquipoRepository EquipoRepository;
	@Autowired
	private TemporadaRepository TemporadaRepository;


	@RequestMapping("/equipo/{temporada}")
	public String equipoList( Model model, @PathVariable long temporada) {
		
		model.addAttribute("equipos", EquipoRepository.findByTemporada(TemporadaRepository.findOne(temporada)));
		model.addAttribute("temporada", temporada);
		
		return "equipoList_template";
	}
	
	@RequestMapping("/equipo/detail/{equipo}")
	public String equipoDetail( Model model, @PathVariable long equipo) {
		
		model.addAttribute("equipo", EquipoRepository.findOne(equipo));
		
		return "equipoDetail_template";
	}
	
	@RequestMapping("/equipo/nuevo_form/{temporada}")
	public String equipoNewForm( Model model, @PathVariable int temporada) {
	model.addAttribute("temporada", temporada);
		return "equipoNewForm_template";
	}
	
	@PostMapping("/equipo/add")
	public String equipoAdd( Model model, Equipo equipo, @RequestParam long temporada) {
		equipo.setTemporada( TemporadaRepository.findOne(temporada) );
		EquipoRepository.save(equipo);
		model.addAttribute("temporada", equipo.getTemporada());
		model.addAttribute("nombre", equipo.getNombre());

		return "equipoSave_template";
	}

}