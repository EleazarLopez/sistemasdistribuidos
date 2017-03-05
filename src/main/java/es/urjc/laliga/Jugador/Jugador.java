package es.urjc.laliga.Jugador;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.urjc.laliga.Equipo.Equipo;

@Entity
public class Jugador
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nombre;
	private String dorsal;
	private String dni;
	@ManyToOne(cascade=CascadeType.ALL)
	private Equipo equipo;
	
	public Jugador() {}
	public Jugador(String nombre, String dorsal, String dni) {
		
	}
	public String getNombre() { 
		return nombre;
	}
	public String getDorsal() { 
		return dorsal;
	}
	public String getDNI() { 
		return dni;
	}
	
	public void setNombre( String nombre) { 
		this.nombre = nombre;
	}
	public void setDorsal( String dorsal) { 
		this.dorsal = dorsal;
	}
	public void setDni( String dni) { 
		this.dni = dni;
	}
	public Equipo getEquipo() { 
		return equipo;
	}
	public void setEquipo( Equipo equipo ){
		this.equipo = equipo;
	}
}