package se.vidioten.databas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Film;

import java.util.List;


@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findAllByKategori(String kategori);
    List<Film> findAllByKund_Personnummer(String personnummer);

    Film findByProduktnummer(Long produknummer);
}
