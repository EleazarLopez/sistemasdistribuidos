package es.urjc.laliga.Partido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import es.urjc.laliga.json.*;
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
	
	@Autowired
    MailSender mailSender;

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
		
		//asi sería la llamada, pero como no funciona con un certificado autofirmado porque no pasa la verificación de httpClient llamo a la función directamente
		/*RestTemplate restTemplate = new RestTemplate();
		Quote quote = restTemplate.getForObject("https://localhost:8443/email/trigger", Quote.class);
		System.out.println(quote.toString());*/
		
		
		this.sendMail(partido.getEquipo_c().getCorreo());
		this.sendMail(partido.getEquipo_v().getCorreo());
		return "partidoSave_template";
	}
	
	private String sendMail( String to){
    	
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hay una actualización en uno de tus partidos");
        message.setTo(to);
        message.setFrom("beny.aux@gmail.com");
        try {
            mailSender.send(message);
            return "{\"message\": \"OK\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"message\": \"Error\", \"error\":" + e +  "}";
        }
    }

}