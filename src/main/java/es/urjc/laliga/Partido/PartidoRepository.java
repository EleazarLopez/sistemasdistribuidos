package es.urjc.laliga.Partido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.laliga.Temporada.Temporada;

public interface PartidoRepository extends JpaRepository<Partido, Long> {
	List<Partido> findByTemporada(Temporada temporada);
	List<Partido> findByTemporadaOrderByFecha(Temporada temporada);
}