package es.urjc.laliga.Partido;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import es.urjc.laliga.Equipo.Equipo;
import es.urjc.laliga.Temporada.Temporada;

@Entity
public class Partido
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@OneToOne(cascade=CascadeType.ALL)
	private Equipo equipo_c;
	@OneToOne(cascade=CascadeType.ALL)
	private Equipo equipo_v;
	private String goles_c;
	private String goles_v;
	private Date fecha;
	@ManyToOne(cascade=CascadeType.ALL)
	private Temporada temporada;
	
	public Partido() {}
	public Partido(Equipo equipo_c, Equipo equipo_v, Temporada temporada) {
		
	}
	
	public Temporada getTemporada() { 
		return temporada;
	}
	public void setTemporada( Temporada temporada ){
		this.temporada = temporada;
	}
	
	public Equipo getEquipo_c() { 
		return equipo_c;
	}
	public void setEquipo_c( Equipo equipo ){
		this.equipo_c = equipo;
	}
	public Equipo getEquipo_v() { 
		return equipo_v;
	}
	public void setEquipo_v( Equipo equipo ){
		this.equipo_v = equipo;
	}
	
	public Date getFecha() { 
		return fecha;
	}
	
	public void setFecha( Date fecha) { 
		this.fecha = fecha;
	}
	
	public void setResultado(String goles_c, String goles_v ){
		this.goles_c = goles_c;
		this.goles_v = goles_v;
	}
	
	public String getResultado(){
		String text;
		if (this.goles_c != null || this.goles_v != null){
			text = " - ";
		}else{
			text = this.goles_c + " - " + this.goles_v;
		}
		return text;
	}
	

}