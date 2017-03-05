package es.urjc.laliga.Equipo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.laliga.Temporada.Temporada;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
	
	List<Equipo> findByTemporada(Temporada temporada);

}