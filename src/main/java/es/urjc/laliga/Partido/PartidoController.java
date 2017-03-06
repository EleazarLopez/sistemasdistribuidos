package es.urjc.laliga.Partido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.laliga.Equipo.EquipoRepository;
import es.urjc.laliga.Temporada.TemporadaRepository;

@Controller
public class PartidoController {
	
	@Autowired
	private TemporadaRepository TemporadaRepository;
	
	@Autowired
	private PartidoRepository PartidoRepository;
	
	@Autowired
	private EquipoRepository EquipoRepository;

	@RequestMapping("/partido/{temporada}")
	public String partidoList( Model model, @PathVariable long temporada) {
		
		model.addAttribute("partidos", PartidoRepository.findByTemporada(TemporadaRepository.findOne(temporada)));
		
		return "partidoList_template";
	}
	
	@RequestMapping("/partido/detail/{id}")
	public String partidoDetail( Model model, @PathVariable long id) {
		
		model.addAttribute("partido", PartidoRepository.findOne(id));
		
		return "partidoDetail_template";
	}
	
	@RequestMapping("/partido/nuevo_form/{temporada}")
	public String partidoNewForm( Model model, @PathVariable long temporada) {
		model.addAttribute("temporada", temporada);
		model.addAttribute("equipos", EquipoRepository.findByTemporada(TemporadaRepository.findOne(temporada)));
		return "partidoNewForm_template";
	}
	
	@PostMapping("/partido/add")
	public String partidoAdd( Model model, Partido partido, @RequestParam long temporada) {
		partido.setTemporada( TemporadaRepository.findOne(temporada) );
		PartidoRepository.save(partido);
		model.addAttribute("partido", partido);

		return "partidoSave_template";
	}
	
	@PostMapping("/partido/update")
	public String partidoUpdate( Model model, @RequestParam long id, @RequestParam String goles_c, @RequestParam String goles_v) {
		Partido partido = PartidoRepository.findOne(id);
		partido.setResultado(goles_c, goles_v);
		PartidoRepository.save(partido);
		model.addAttribute("partido", partido);

		return "partidoSave_template";
	}

}