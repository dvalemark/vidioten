package se.vidioten.databas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Film;



@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
}
