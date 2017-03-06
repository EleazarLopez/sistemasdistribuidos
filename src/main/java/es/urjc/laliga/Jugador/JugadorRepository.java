package es.urjc.laliga.Jugador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.urjc.laliga.Temporada.Temporada;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {

	List<Jugador> findByEquipo_Temporada( Temporada temporada);
}