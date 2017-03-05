package es.urjc.laliga.Sancion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.laliga.Temporada.Temporada;

public interface SancionRepository extends JpaRepository<Sancion, Long> {
	
	List<Sancion> findByTemporada(Temporada temporada);

}