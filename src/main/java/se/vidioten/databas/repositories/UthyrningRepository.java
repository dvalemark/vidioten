package se.vidioten.databas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Film;
import se.vidioten.databas.entities.Uthyrning;

import java.util.List;

@Repository
public interface UthyrningRepository extends JpaRepository<Uthyrning,Long> {

    Uthyrning findByFilmAndAndInlamningsdatumIsNull(Film film);
    List<Uthyrning> findByInlamningsdatumIsNull();
    List<Uthyrning> findByInlamningsdatumIsNotNull();
}
