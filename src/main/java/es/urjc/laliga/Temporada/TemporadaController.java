package es.urjc.laliga.Temporada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.laliga.Partido.PartidoRepository;

@Controller
public class TemporadaController {
	
	@Autowired
	private TemporadaRepository TemporadaRepository;
	@Autowired
	private PartidoRepository PartidoRepository;

	@RequestMapping("/temporada")
	public String temporadaList( Model model) {
		
		model.addAttribute("temporadas", TemporadaRepository.findAll());
		
		return "temporadaList_template";
	}
	
	@RequestMapping("/temporada/nuevo_form")
	public String temporadaNewForm( Model model) {
		
		return "temporadaNewForm_template";
	}
	
	@PostMapping("/temporada/add")
	public String temporadaAdd( Temporada temporada) {
		TemporadaRepository.save(temporada);

		return "temporadaSave_template";
	}
	
	@RequestMapping("/temporada/{id}")
	public String temporadaDetail( Model model, @PathVariable long id) {
		model.addAttribute("temporada", TemporadaRepository.findOne(id));
		model.addAttribute("calendario", PartidoRepository.findByTemporadaOrderByFecha(TemporadaRepository.findOne(id)));
		
		return "temporadaDetail_template";
	}

}