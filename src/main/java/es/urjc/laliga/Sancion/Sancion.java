package es.urjc.laliga.Sancion;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.urjc.laliga.Jugador.Jugador;
import es.urjc.laliga.Temporada.Temporada;

@Entity
public class Sancion
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Temporada temporada;
	@ManyToOne(cascade=CascadeType.ALL)
	private Jugador jugador;
	private Date fecha_inicio;
	private Date fecha_fin;
	
	public Sancion() {}
	public Sancion(Jugador jugador, Date fecha_inicio, Date fecha_fin) {
		
	}	
	
	public Temporada getTemporada() { 
		return temporada;
	}
	public void setTemporada( Temporada temporada ){
		this.temporada = temporada;
	}
	
	public Jugador getJugador() { 
		return jugador;
	}
	public void setJugador( Jugador jugador ){
		this.jugador = jugador;
	}
	
	public Date getFecha_inicio() { 
		return fecha_inicio;
	}
	
	public void setFecha_inicio( Date fecha) { 
		this.fecha_inicio = fecha;
	}
	
	public Date getFecha_fin() { 
		return fecha_fin;
	}
	
	public void setFecha_fin( Date fecha) { 
		this.fecha_fin = fecha;
	}	

}