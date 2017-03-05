package es.urjc.laliga.Equipo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import es.urjc.laliga.Jugador.Jugador;
import es.urjc.laliga.Temporada.Temporada;

@Entity
public class Equipo
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String correo;
	private String telefono;
	@OneToMany(mappedBy="equipo") 
	private List<Jugador> jugadores;
	@ManyToOne(cascade=CascadeType.ALL)
	private Temporada temporada;
	
	public Equipo() {}
	public Equipo(String nombre, String correo, String telefono) {
		
	}
	public String getNombre() { 
		return nombre;
	}
	public String getCorreo() { 
		return correo;
	}
	public String getTelefono() { 
		return telefono;
	}
	
	public void setNombre( String nombre) { 
		this.nombre = nombre;
	}
	public void setCorreo( String correo) { 
		this.correo = correo;
	}
	public void setTelefono(String telefono) { 
		this.telefono = telefono;
	}
	public Temporada getTemporada() { 
		return temporada;
	}
	public void setTemporada( Temporada temporada ){
		this.temporada = temporada;
	}
	
	public List<Jugador> getJugadores() { 
		return jugadores;
	}
	
	

}