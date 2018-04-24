package se.vidioten.databas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.vidioten.databas.entities.Film;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Page<Film> findAllByKategori(String kategori, org.springframework.data.domain.Pageable pageable);
    List<Film> findAllByKund_Personnummer(String personnummer);

    Film findByProduktnummer(Long produknummer);
}
