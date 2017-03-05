package es.urjc.laliga.Temporada;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import es.urjc.laliga.Equipo.Equipo;

@Entity
public class Temporada
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	@OneToOne(cascade=CascadeType.ALL)
	private Equipo ganador;
	
	public Temporada() {}
	public Temporada(String nombre) {
		super();
		this.nombre = nombre;
		
	}
	public String getNombre() { 
		return nombre;
	}
	
	public void setNombre(String nombre) { 
		this.nombre = nombre;
	}
	public String getGanador() { 
		return ganador.getNombre();
	}

}